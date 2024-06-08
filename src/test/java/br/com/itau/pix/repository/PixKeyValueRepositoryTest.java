package br.com.itau.pix.repository;

import br.com.itau.pix.dto.model.PixKeyValueDTO;
import br.com.itau.pix.enumerators.StatusEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.test.context.ActiveProfiles;


import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@EnableMongoRepositories(basePackageClasses = {PixKeyRepository.class, PixKeyValueRepository.class})
@ActiveProfiles("test")
public class PixKeyValueRepositoryTest {

    @Autowired
    private PixKeyValueRepository pixKeyValueRepository;


    @BeforeEach
    public void setUp() {
        // Limpar a coleção antes de cada teste
        pixKeyValueRepository.deleteAll();

        // Inserir dados de teste
        PixKeyValueDTO pixKeyValue = new PixKeyValueDTO();
        pixKeyValue.setId("1");
        pixKeyValue.setKeyType("CPF");
        pixKeyValue.setKeyValue("12345678900");
        pixKeyValue.setTimestampInclusion("2024-06-08T19:18:16.902");
        pixKeyValue.setTimestampExclusion("2024-06-09T19:18:16.902");
        pixKeyValue.setStatus(StatusEnum.ACTIVE);

        pixKeyValueRepository.save(pixKeyValue);
    }

    @Test
    public void testFindByKeyType() {
        Optional<List<PixKeyValueDTO>> result = pixKeyValueRepository.findByKeyType("CPF");
        assertThat(result).isPresent();
        assertThat(result.get()).hasSize(1);
        assertThat(result.get().get(0).getKeyValue()).isEqualTo("12345678900");
    }

    @Test
    public void testFindByKeyType_NotFound() {
        Optional<List<PixKeyValueDTO>> result = pixKeyValueRepository.findByKeyType("EMAIL");
        assertThat(result).isPresent();
        assertThat(result.get()).isEmpty();
    }

    @Test
    public void testFindByTimestampInclusion() {
        Optional<List<PixKeyValueDTO>> result = pixKeyValueRepository.findByTimestampInclusion("2024-06-08T19:18:16.902");
        assertThat(result).isPresent();
        assertThat(result.get()).hasSize(1);
        assertThat(result.get().get(0).getKeyType()).isEqualTo("CPF");
    }

    @Test
    public void testFindByTimestampInclusion_NotFound() {
        Optional<List<PixKeyValueDTO>> result = pixKeyValueRepository.findByTimestampInclusion("2023-01-01T00:00:00.000");
        assertThat(result).isPresent();
        assertThat(result.get()).isEmpty();
    }

    @Test
    public void testFindByTimestampExclusion() {
        Optional<List<PixKeyValueDTO>> result = pixKeyValueRepository.findByTimestampExclusion("2024-06-09T19:18:16.902");
        assertThat(result).isPresent();
        assertThat(result.get()).hasSize(1);
        assertThat(result.get().get(0).getKeyType()).isEqualTo("CPF");
    }

    @Test
    public void testFindByTimestampExclusion_NotFound() {
        Optional<List<PixKeyValueDTO>> result = pixKeyValueRepository.findByTimestampExclusion("2023-01-01T00:00:00.000");
        assertThat(result).isPresent();
        assertThat(result.get()).isEmpty();
    }

}
