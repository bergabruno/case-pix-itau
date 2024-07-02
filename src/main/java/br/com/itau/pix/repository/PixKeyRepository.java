package br.com.itau.pix.repository;

import br.com.itau.pix.dto.model.PixKeyDTO;
import br.com.itau.pix.enumerators.AccountTypeEnum;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface PixKeyRepository extends MongoRepository<PixKeyDTO, String>, PixKeyRepositoryCustom {

    Optional<PixKeyDTO> findByKeyValue(String keyValue);

    List<PixKeyDTO> findAllByAccountTypeAndAgencyNumberAndAccountNumber(AccountTypeEnum accountType, Integer agencyNumber, Integer accountNumber);
}
