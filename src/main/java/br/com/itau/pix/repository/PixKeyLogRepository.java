package br.com.itau.pix.repository;

import br.com.itau.pix.dto.model.PixKeyLogDTO;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PixKeyLogRepository extends MongoRepository<PixKeyLogDTO, String> {

    Optional<PixKeyLogDTO> findByKeyValueId(String id);
}
