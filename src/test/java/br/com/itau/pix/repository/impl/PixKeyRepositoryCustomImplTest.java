package br.com.itau.pix.repository.impl;

import br.com.itau.pix.dto.model.PixKeyDTO;
import br.com.itau.pix.exception.ResourceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class PixKeyRepositoryCustomImplTest {

    @Mock
    private MongoTemplate mongoTemplate;

    @InjectMocks
    private PixKeyRepositoryCustomImpl pixKeyRepositoryCustom;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindByCustomCriteria() {
        Map<String, Object> criteria = Map.of("keyType", "EMAIL");
        Pageable pageable = PageRequest.of(0, 10);
        PixKeyDTO pixKeyDTO = new PixKeyDTO(); // Configure seu PixKeyDTO conforme necessário
        List<PixKeyDTO> pixKeyDTOList = List.of(pixKeyDTO);

        when(mongoTemplate.count(any(Query.class), any(Class.class))).thenReturn(1L);
        when(mongoTemplate.find(any(Query.class), any(Class.class))).thenReturn(pixKeyDTOList);

        Page<PixKeyDTO> result = pixKeyRepositoryCustom.findByCustomCriteria(criteria, pageable);

        assertEquals(1, result.getTotalElements());
        assertEquals(1, result.getContent().size());
        assertEquals(pixKeyDTO, result.getContent().get(0));
    }

    @Test
    public void testFindByCustomCriteria_NotFound() {
        Map<String, Object> criteria = Map.of("keyType", "EMAIL");
        Pageable pageable = PageRequest.of(0, 10);

        when(mongoTemplate.count(any(Query.class), any(Class.class))).thenReturn(0L);
        when(mongoTemplate.find(any(Query.class), any(Class.class))).thenReturn(Collections.emptyList());

        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
            pixKeyRepositoryCustom.findByCustomCriteria(criteria, pageable);
        });

        assertEquals("Invalid ID", exception.getMessage());
        assertEquals("Query", exception.getFieldErrorDTO().getField());
    }
}
