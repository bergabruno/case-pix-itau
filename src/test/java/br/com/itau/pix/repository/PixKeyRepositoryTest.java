package br.com.itau.pix.repository;

import br.com.itau.pix.dto.model.PixKeyDTO;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@EnableMongoRepositories(basePackageClasses = {PixKeyRepository.class, PixKeyValueRepository.class})
@ActiveProfiles("test")
public class PixKeyRepositoryTest {

    @Autowired
    private PixKeyRepository pixKeyRepository;

    @BeforeEach
    public void setUp() {
        pixKeyRepository.deleteAll();

        // Inserir dados de teste
        PixKeyDTO pixKey = new PixKeyDTO();
        pixKey.setId(UUID.randomUUID().toString());
        pixKey.setAccountType("poupanca");
        pixKey.setAgencyNumber(1234);
        pixKey.setAccountNumber(567890);
        pixKey.setAccountHolderFirstName("John");
        pixKey.setAccountHolderLastName("Doe");

        pixKeyRepository.save(pixKey);
    }

    @Test
    public void testFindByAccountNumberAndAgencyNumber() {
        Optional<PixKeyDTO> result = pixKeyRepository.findByAccountNumberAndAgencyNumber(567890, 1234);
        assertThat(result).isPresent();
        assertThat(result.get().getAccountHolderFirstName()).isEqualTo("John");
        assertThat(result.get().getAccountHolderLastName()).isEqualTo("Doe");
    }

    @Test
    public void testFindByAccountNumberAndAgencyNumber_NotFound() {
        Optional<PixKeyDTO> result = pixKeyRepository.findByAccountNumberAndAgencyNumber(999999, 1234);
        assertThat(result).isNotPresent();
    }

}
