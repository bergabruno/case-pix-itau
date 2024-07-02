package br.com.itau.pix.validation.chain;

import br.com.itau.pix.dto.error.FieldErrorDTO;
import br.com.itau.pix.dto.support.SupportDeletePixKeyDTO;
import br.com.itau.pix.exception.ValidationException;
import br.com.itau.pix.validation.validators.base.DeletePixKeyValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class DeletePixKeyValidationChainTest {

    @Mock
    private DeletePixKeyValidator validator1;

    @Mock
    private DeletePixKeyValidator validator2;

    @InjectMocks
    private DeletePixKeyValidationChain validationChain;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        validationChain = new DeletePixKeyValidationChain(List.of(validator1, validator2));
    }

    @Test
    public void testValidate_NoErrors() {
        SupportDeletePixKeyDTO supportDeletePixKeyDTO = mock(SupportDeletePixKeyDTO.class);

        when(validator1.validate(any(SupportDeletePixKeyDTO.class))).thenReturn(null);
        when(validator2.validate(any(SupportDeletePixKeyDTO.class))).thenReturn(null);

        assertDoesNotThrow(() -> validationChain.validate(supportDeletePixKeyDTO));

        verify(validator1, times(1)).validate(supportDeletePixKeyDTO);
        verify(validator2, times(1)).validate(supportDeletePixKeyDTO);
    }

    @Test
    public void testValidate_WithErrors() {
        SupportDeletePixKeyDTO supportDeletePixKeyDTO = mock(SupportDeletePixKeyDTO.class);
        FieldErrorDTO error1 = new FieldErrorDTO("field1", "error1");
        FieldErrorDTO error2 = new FieldErrorDTO("field2", "error2");

        when(validator1.validate(any(SupportDeletePixKeyDTO.class))).thenReturn(error1);
        when(validator2.validate(any(SupportDeletePixKeyDTO.class))).thenReturn(error2);

        ValidationException exception = assertThrows(ValidationException.class, () -> validationChain.validate(supportDeletePixKeyDTO));

        assertEquals("Validation Failed", exception.getMessage());
        assertEquals(2, exception.getFieldErrors().size());
        assertTrue(exception.getFieldErrors().contains(error1));
        assertTrue(exception.getFieldErrors().contains(error2));

        verify(validator1, times(1)).validate(supportDeletePixKeyDTO);
        verify(validator2, times(1)).validate(supportDeletePixKeyDTO);
    }
}
