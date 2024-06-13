package br.com.itau.pix.service.impl;

import br.com.itau.pix.dto.model.PixKeyDTO;
import br.com.itau.pix.dto.request.AccountKeyRequestBodyDTO;
import br.com.itau.pix.dto.response.PixKeyResponseDeleteDTO;
import br.com.itau.pix.dto.response.PixKeyResponseGetDTO;
import br.com.itau.pix.dto.response.PixKeyResponsePatchDTO;
import br.com.itau.pix.dto.response.PixKeyResponsePostDTO;
import br.com.itau.pix.enumerators.AccountTypeEnum;
import br.com.itau.pix.enumerators.PersonType;
import br.com.itau.pix.enumerators.StatusEnum;
import br.com.itau.pix.exception.*;
import br.com.itau.pix.repository.PixKeyRepository;
import br.com.itau.pix.service.PixKeyService;
import br.com.itau.pix.util.DateFormatUtil;
import br.com.itau.pix.validator.KeyValueValidator;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
@AllArgsConstructor
@Slf4j
public class PixKeyServiceImpl implements PixKeyService {

    private final KeyValueValidator keyValueValidator;

    private final PixKeyRepository repository;


    @Override
    public PixKeyResponsePostDTO savePixKey(AccountKeyRequestBodyDTO requestPostDTO) {
        log.info("Save pix key: {}", requestPostDTO);

        keyValueValidator.validate(requestPostDTO.getKeyType(), requestPostDTO.getKeyValue());

        PixKeyDTO pixKeyDTO = findExistingPixKeyValue(requestPostDTO);

        repository.save(pixKeyDTO);

        return new PixKeyResponsePostDTO(pixKeyDTO.getId());
    }

    @Override
    public Page<PixKeyResponseGetDTO> getByParams(Map<String, Object> params, Integer page, Integer size) {

        if (params.containsKey("agencyNumber")) {
            params.put("agencyNumber", Integer.valueOf((String) params.get("agencyNumber")));
        }
        if (params.containsKey("accountNumber")) {
            params.put("accountNumber", Integer.valueOf((String) params.get("accountNumber")));
        }

        if(params.containsKey("id") && params.size() > 1){
            throw new InvalidParamsException("se informar o id nao pode ter outro campo");
        }

        if(params.containsKey("timestampInclusion") && params.containsKey("timestampExclusion")){
            throw new InvalidParamsException("nao eh possivel realizar query por meio do timestamp de inclusao e exclusao");
        }

        params.remove("size");
        params.remove("page");

        Pageable pageable = PageRequest.of(page, size);
        Page<PixKeyDTO> pixKeyDTOPage = repository.findByCustomCriteria(params, pageable);
        if(pixKeyDTOPage.getTotalElements() == 0) {
            throw new ResourceNotFoundException("Nao existe nenhum dado com essa query informada");
        }

        return pixKeyDTOPage.map(PixKeyResponseGetDTO::fromPixKeyDTO);
    }

    @Override
    public PixKeyResponsePatchDTO update(AccountKeyRequestBodyDTO accountKeyRequestBodyDTO) {
        log.info("Update pix key: {}", accountKeyRequestBodyDTO);

        keyValueValidator.validate(accountKeyRequestBodyDTO.getKeyType(), accountKeyRequestBodyDTO.getKeyValue());

        PixKeyDTO pixKeyDTO = this.findById(accountKeyRequestBodyDTO.getId());

        if (pixKeyDTO.getStatus() == StatusEnum.INACTIVE) {
            throw new AlreadyInactiveException("A chave se encontra desativada, nao eh possivel alterar uma chave desativada.");
        }

        if (!accountKeyRequestBodyDTO.getKeyType().equalsIgnoreCase(pixKeyDTO.getKeyType())) {
            throw new InvalidKeyTypeException("O tipo da chave informada nao coincide com o tipo de chave cadastrada.");
        }

        if (accountKeyRequestBodyDTO.getKeyValue().equalsIgnoreCase(pixKeyDTO.getKeyValue())) {
            throw new DuplicateKeyException("O valor da chave informada eh o mesmo cadastrado.");
        }

        pixKeyDTO.setKeyValue(accountKeyRequestBodyDTO.getKeyValue());
        pixKeyDTO.setTimestampInclusion(DateFormatUtil.formatToString(LocalDateTime.now()));

        pixKeyDTO = repository.save(pixKeyDTO);

        return new PixKeyResponsePatchDTO(pixKeyDTO);
    }

    @Override
    public PixKeyResponseDeleteDTO deletePixKey(String pixKeyId) {
        PixKeyDTO pixKeyDTO = this.findById(pixKeyId);

        if (StatusEnum.INACTIVE == pixKeyDTO.getStatus()) {
            throw new AlreadyInactiveException("A chave ja se encontra desativada!");
        }

        pixKeyDTO.setStatus(StatusEnum.INACTIVE);
        pixKeyDTO.setTimestampExclusion(DateFormatUtil.formatToString(LocalDateTime.now()));

        pixKeyDTO = repository.save(pixKeyDTO);

        return new PixKeyResponseDeleteDTO(pixKeyDTO);
    }

    private PixKeyDTO findExistingPixKeyValue(AccountKeyRequestBodyDTO accountKeyRequestBodyDTO) {

        //Validar se alguem ja cadastrou a chave
        Optional<PixKeyDTO> accountKeyOptional = repository.findByKeyValue(accountKeyRequestBodyDTO.getKeyValue());

        if (accountKeyOptional.isPresent()) {
            PixKeyDTO accountKeyFromDB = accountKeyOptional.get();

            validateDuplicateKey(accountKeyFromDB, accountKeyRequestBodyDTO);
        }

        //validacao de quantidade de chaves por conta
        List<PixKeyDTO> list = repository.findAllByAccountTypeAndAgencyNumberAndAccountNumber(AccountTypeEnum.getAccountType(accountKeyRequestBodyDTO.getAccountType()), accountKeyRequestBodyDTO.getAgencyNumber(), accountKeyRequestBodyDTO.getAccountNumber());

        if (!list.isEmpty()) {
            validateKeyCount(list, accountKeyRequestBodyDTO);
        }

        return new PixKeyDTO(accountKeyRequestBodyDTO);
    }

    private void validateDuplicateKey(PixKeyDTO pixKeyDTO, AccountKeyRequestBodyDTO accountKeyRequestBodyDTO) {
        String isInactive = pixKeyDTO.getStatus() == StatusEnum.INACTIVE ? "INATIVA" : "ATIVA";

        String accountCombination = pixKeyDTO.getAccountType() + "|" + pixKeyDTO.getAgencyNumber().toString() + pixKeyDTO.getAccountNumber().toString();
        String accountCombinationRequest = accountKeyRequestBodyDTO.getAccountType() + "|" + accountKeyRequestBodyDTO.getAgencyNumber().toString() + accountKeyRequestBodyDTO.getAccountNumber().toString();

        if (accountCombination.equalsIgnoreCase(accountCombinationRequest)) {
            throw new DuplicateKeyException("A chave que você tentou cadastrar, já está cadastrada em sua conta. O status da chave eh: " + isInactive);
        } else {
            throw new DuplicateKeyException("A chave que você tentou cadastrar, já está cadastrada em outra conta.");
        }
    }

    private void validateKeyCount(List<PixKeyDTO> pixKeyDTO, AccountKeyRequestBodyDTO accountKeyRequestBodyDTO) {

        PersonType personType = getPersonType(pixKeyDTO);

        if (personType == PersonType.UNDEFINED && pixKeyDTO.size() >= 4) {
            if (!accountKeyRequestBodyDTO.getKeyType().equalsIgnoreCase("CPF") && !accountKeyRequestBodyDTO.getKeyType().equalsIgnoreCase("CNPJ")) {
                throw new InvalidKeyValueException("A próxima chave cadastrada deve ser um CPF ou CNPJ.");
            }
        }

        if (personType == PersonType.FISICA && pixKeyDTO.size() >= 5) {
            throw new InvalidKeyValueException("Você não pode cadastrar mais de 5 chaves para pessoa física.");
        }

        if (personType == PersonType.JURIDICA && pixKeyDTO.size() >= 20) {
            throw new InvalidKeyValueException("Você não pode cadastrar mais de 20 chaves para pessoa jurídica.");
        }
    }


    private PersonType getPersonType(List<PixKeyDTO> activeKeys) {
        boolean isPessoaFisica = activeKeys.stream().anyMatch(key -> key.getKeyType().equalsIgnoreCase("CPF"));
        boolean isPessoaJuridica = activeKeys.stream().anyMatch(key -> key.getKeyType().equalsIgnoreCase("CNPJ"));

        if (isPessoaFisica) {
            return PersonType.FISICA;
        } else if (isPessoaJuridica) {
            return PersonType.JURIDICA;
        } else {
            return PersonType.UNDEFINED;
        }
    }

    private PixKeyDTO findById(String id){
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Nao foi encontrado nenhum id"));
    }
}
