package br.com.itau.pix.repository;

import br.com.itau.pix.dto.model.PixKeyValueDTO;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PixKeyValueRepository extends MongoRepository<PixKeyValueDTO, String> {

    Optional<List<PixKeyValueDTO>> findByKeyType(String keyType);

    Optional<List<PixKeyValueDTO>> findByTimestampInclusion(String timestampInclusion);

    Optional<List<PixKeyValueDTO>> findByTimestampExclusion(String timestampExclusion);

    Optional<PixKeyValueDTO> findByKeyValue(String keyValue);

    boolean existsById(String keyId);

    boolean existsByKeyValue(String keyValue);
}
