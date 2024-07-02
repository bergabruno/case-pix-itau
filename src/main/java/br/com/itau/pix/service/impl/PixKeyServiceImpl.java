package br.com.itau.pix.service.impl;

import br.com.itau.pix.dto.error.FieldErrorDTO;
import br.com.itau.pix.dto.model.PixKeyDTO;
import br.com.itau.pix.dto.model.PixKeyLogDTO;
import br.com.itau.pix.dto.request.PixKeyRequestBodyDTO;
import br.com.itau.pix.dto.response.PixKeyResponseDeleteDTO;
import br.com.itau.pix.dto.response.PixKeyResponseGetDTO;
import br.com.itau.pix.dto.response.PixKeyResponsePatchDTO;
import br.com.itau.pix.dto.response.PixKeyResponsePostDTO;
import br.com.itau.pix.dto.support.SupportDeletePixKeyDTO;
import br.com.itau.pix.dto.support.SupportGetPixKeyDTO;
import br.com.itau.pix.dto.support.SupportSavePixKeyDTO;
import br.com.itau.pix.dto.support.SupportUpdatePixKeyDTO;
import br.com.itau.pix.enumerators.AccountTypeEnum;
import br.com.itau.pix.enumerators.StatusEnum;
import br.com.itau.pix.exception.*;
import br.com.itau.pix.repository.PixKeyLogRepository;
import br.com.itau.pix.repository.PixKeyRepository;
import br.com.itau.pix.service.PixKeyLogService;
import br.com.itau.pix.service.PixKeyService;
import br.com.itau.pix.util.DateFormatUtil;
import br.com.itau.pix.validation.chain.DeletePixKeyValidationChain;
import br.com.itau.pix.validation.chain.GetPixKeyValidationChain;
import br.com.itau.pix.validation.chain.SavePixKeyValidationChain;
import br.com.itau.pix.validation.chain.UpdatePixKeyValidationChain;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@AllArgsConstructor
@Slf4j
@Service
public class PixKeyServiceImpl implements PixKeyService {

    private final SavePixKeyValidationChain saveValidationChain;
    private final UpdatePixKeyValidationChain updateValidationChain;
    private final DeletePixKeyValidationChain deleteValidationChain;
    private final GetPixKeyValidationChain getValidationChain;
    private final PixKeyRepository repository;
    private final PixKeyLogService logService;

    @Override
    public PixKeyResponsePostDTO savePixKey(PixKeyRequestBodyDTO pixKeyRequestBodyDTO) {
        log.info("Save pix key: {}", pixKeyRequestBodyDTO);

        saveValidationChain.validate(getSupportSave(pixKeyRequestBodyDTO));

        PixKeyDTO pixKeyDTO = repository.save(new PixKeyDTO(pixKeyRequestBodyDTO));

        logService.saveLogAsync(new PixKeyLogDTO(pixKeyDTO, "Adding pix key: " + pixKeyRequestBodyDTO.getKeyValue(), "INSERT"));

        return new PixKeyResponsePostDTO(pixKeyDTO.getId());
    }

    @Override
    public Page<PixKeyResponseGetDTO> getByParams(HashMap<String, Object> params, Integer page, Integer size) {
        adjustParams(params);

        log.info("Get by params: {}", params);

        getValidationChain.validate(new SupportGetPixKeyDTO(params));

        Pageable pageable = PageRequest.of(page, size);
        Page<PixKeyDTO> pixKeyDTOPage = repository.findByCustomCriteria(params, pageable);

        return pixKeyDTOPage.map(PixKeyResponseGetDTO::fromPixKeyDTO);
    }

    @Override
    public PixKeyResponsePatchDTO update(PixKeyRequestBodyDTO pixKeyRequestBodyDTO) {
        log.info("Update pix key: {}", pixKeyRequestBodyDTO);

        SupportUpdatePixKeyDTO supportUpdatePixKeyDTO = getSupportUpdate(pixKeyRequestBodyDTO);
        PixKeyDTO pixKeyDTO = supportUpdatePixKeyDTO.getPixKeyDTO();

        updateValidationChain.validate(supportUpdatePixKeyDTO);

        String previousPixKeyValue = pixKeyDTO.getKeyValue();

        pixKeyDTO.setKeyValue(pixKeyRequestBodyDTO.getKeyValue());
        pixKeyDTO.setTimestampInclusion(DateFormatUtil.formatToString(LocalDateTime.now()));

        logService.saveLogAsync(new PixKeyLogDTO(pixKeyDTO, "Previous Key Value: " + previousPixKeyValue + " New pix key: " + pixKeyRequestBodyDTO.getKeyValue(), "UPDATE"));
        pixKeyDTO = repository.save(pixKeyDTO);

        return new PixKeyResponsePatchDTO(pixKeyDTO);
    }

    @Override
    public PixKeyResponseDeleteDTO deletePixKey(String pixKeyId) {
        log.info("Delete a pix key: {}", pixKeyId);

        PixKeyDTO pixKeyDTO = this.findById(pixKeyId);

        deleteValidationChain.validate(new SupportDeletePixKeyDTO(pixKeyDTO));

        pixKeyDTO.setStatus(StatusEnum.INACTIVE);
        pixKeyDTO.setTimestampExclusion(DateFormatUtil.formatToString(LocalDateTime.now()));

        pixKeyDTO = repository.save(pixKeyDTO);

        logService.saveLogAsync(new PixKeyLogDTO(pixKeyDTO, "Inactive pix key: " + pixKeyDTO.getKeyValue(), "DELETE"));
        return new PixKeyResponseDeleteDTO(pixKeyDTO);
    }

    private PixKeyDTO findByKeyValue(String keyValue) {
        return repository.findByKeyValue(keyValue).orElse(null);
    }

    private PixKeyDTO findById(String id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Id Invalido", new FieldErrorDTO("ID", "Nao foi encontrada nenhuma Chave Pix com o ID informado")));
    }

    private SupportSavePixKeyDTO getSupportSave(PixKeyRequestBodyDTO requestPostDTO) {
        PixKeyDTO findPixKey = this.findByKeyValue(requestPostDTO.getKeyValue());
        List<PixKeyDTO> findPixKeys = repository.findAllByAccountTypeAndAgencyNumberAndAccountNumber(
                AccountTypeEnum.getAccountType(requestPostDTO.getAccountType()),
                requestPostDTO.getAgencyNumber(),
                requestPostDTO.getAccountNumber());

        return new SupportSavePixKeyDTO(findPixKey, requestPostDTO, findPixKeys);
    }


    private SupportUpdatePixKeyDTO getSupportUpdate(PixKeyRequestBodyDTO requestPostDTO) {
        PixKeyDTO findPixKey = this.findById(requestPostDTO.getId());
        List<PixKeyDTO> findPixKeys = repository.findAllByAccountTypeAndAgencyNumberAndAccountNumber(
                AccountTypeEnum.getAccountType(requestPostDTO.getAccountType()),
                requestPostDTO.getAgencyNumber(),
                requestPostDTO.getAccountNumber());

        PixKeyDTO pixKeyDTOFromValue = repository.findByKeyValue(requestPostDTO.getKeyValue()).orElse(null);
        return new SupportUpdatePixKeyDTO(findPixKey, requestPostDTO, findPixKeys, pixKeyDTOFromValue);
    }

    private static void adjustParams(HashMap<String, Object> params) {
        if (params.containsKey("agencyNumber")) {
            params.put("agencyNumber", Integer.valueOf((String) params.get("agencyNumber")));
        }
        if (params.containsKey("accountNumber")) {
            params.put("accountNumber", Integer.valueOf((String) params.get("accountNumber")));
        }

        params.remove("size");
        params.remove("page");
    }
}
