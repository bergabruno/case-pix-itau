package br.com.itau.pix.repository;

import br.com.itau.pix.dto.model.PixKeyDTO;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PixKeyRepository extends MongoRepository<PixKeyDTO, String> {

    Optional<PixKeyDTO> findByAccountNumberAndAgencyNumber(Integer accountNumber, Integer AgencyNumber);

    Optional<PixKeyDTO> findByAccountCombination(String accountCombination);

}
