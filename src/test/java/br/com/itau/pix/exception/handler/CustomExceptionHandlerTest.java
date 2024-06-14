package br.com.itau.pix.exception.handler;

import br.com.itau.pix.dto.error.ErrorDTO;
import br.com.itau.pix.dto.error.FieldErrorDTO;
import br.com.itau.pix.exception.InvalidAccountTypeException;
import br.com.itau.pix.exception.ResourceNotFoundException;
import br.com.itau.pix.exception.ValidationException;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static java.util.Collections.singletonList;

public class CustomExceptionHandlerTest {

    @Mock
    private HttpServletRequest request;

    private CustomExceptionHandler exceptionHandler;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        exceptionHandler = new CustomExceptionHandler();
    }

    @Test
    public void handleResourceNotFoundException() {
        when(request.getRequestURI()).thenReturn("/test-uri");

        FieldErrorDTO fieldErrorDTO = new FieldErrorDTO("field", "error");
        ResourceNotFoundException ex = new ResourceNotFoundException("Resource not found", fieldErrorDTO);
        ResponseEntity<ErrorDTO> response = exceptionHandler.handleResourceNotFoundException(ex, request);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Resource not found", response.getBody().getMessage());
        assertEquals("/test-uri", response.getBody().getPath());
        assertEquals(1, response.getBody().getFieldErrors().size());
        assertEquals(fieldErrorDTO, response.getBody().getFieldErrors().get(0));
    }

    @Test
    public void handleValidationException() {
        when(request.getRequestURI()).thenReturn("/test-uri");

        FieldErrorDTO fieldErrorDTO = new FieldErrorDTO("field", "error");
        ValidationException ex = new ValidationException("Validation error", List.of(fieldErrorDTO));
        ResponseEntity<ErrorDTO> response = exceptionHandler.handleValidationException(ex, request);

        assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, response.getStatusCode());
        assertEquals("Validation error", response.getBody().getMessage());
        assertEquals("/test-uri", response.getBody().getPath());
        assertEquals(1, response.getBody().getFieldErrors().size());
        assertEquals(fieldErrorDTO, response.getBody().getFieldErrors().get(0));
    }

    @Test
    public void handleMethodArgumentNotValidException() {
        when(request.getRequestURI()).thenReturn("/test-uri");

        BindingResult bindingResult = mock(BindingResult.class);
        when(bindingResult.getFieldErrors()).thenReturn(singletonList(new FieldError("objectName", "field", "error message")));
        MethodArgumentNotValidException ex = new MethodArgumentNotValidException(null, bindingResult);

        ResponseEntity<ErrorDTO> response = exceptionHandler.handleMethodArgumentNotValidException(ex, request);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Validation failed", response.getBody().getMessage());
        assertEquals("/test-uri", response.getBody().getPath());
        assertEquals(1, response.getBody().getFieldErrors().size());
        assertEquals("field", response.getBody().getFieldErrors().get(0).getField());
        assertEquals("error message", response.getBody().getFieldErrors().get(0).getErrorMessage());
    }

    @Test
    public void handleInvalidAccountTypeException() {
        when(request.getRequestURI()).thenReturn("/test-uri");

        InvalidAccountTypeException ex = new InvalidAccountTypeException("Invalid account type");
        ResponseEntity<ErrorDTO> response = exceptionHandler.handleInvalidAccountTypeException(ex, request);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Invalid account type", response.getBody().getMessage());
        assertEquals("/test-uri", response.getBody().getPath());
    }
}
