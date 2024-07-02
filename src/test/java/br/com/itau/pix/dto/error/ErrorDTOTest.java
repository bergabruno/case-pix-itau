package br.com.itau.pix.dto.error;


import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ErrorDTOTest {

    @Test
    public void testErrorDTOConstructorAndGetters() {
        int status = 400;
        String message = "Bad Request";
        LocalDateTime timestamp = LocalDateTime.now();
        String path = "/api/test";
        List<FieldErrorDTO> fieldErrors = new ArrayList<>();
        fieldErrors.add(new FieldErrorDTO("field1", "must not be null"));

        ErrorDTO errorDTO = new ErrorDTO(status, message, timestamp, path, fieldErrors);

        assertEquals(status, errorDTO.getStatus());
        assertEquals(message, errorDTO.getMessage());
        assertEquals(timestamp, errorDTO.getTimestamp());
        assertEquals(path, errorDTO.getPath());
        assertEquals(fieldErrors, errorDTO.getFieldErrors());
    }

    @Test
    public void testErrorDTOSetters() {
        ErrorDTO errorDTO = new ErrorDTO(0, null, null, null, null);

        int status = 400;
        String message = "Bad Request";
        LocalDateTime timestamp = LocalDateTime.now();
        String path = "/api/test";
        String error = "Validation Error";
        String trace = "StackTrace";
        List<FieldErrorDTO> fieldErrors = new ArrayList<>();
        fieldErrors.add(new FieldErrorDTO("field1", "must not be null"));

        errorDTO.setStatus(status);
        errorDTO.setMessage(message);
        errorDTO.setTimestamp(timestamp);
        errorDTO.setPath(path);
        errorDTO.setError(error);
        errorDTO.setTrace(trace);
        errorDTO.setFieldErrors(fieldErrors);

        assertEquals(status, errorDTO.getStatus());
        assertEquals(message, errorDTO.getMessage());
        assertEquals(timestamp, errorDTO.getTimestamp());
        assertEquals(path, errorDTO.getPath());
        assertEquals(error, errorDTO.getError());
        assertEquals(trace, errorDTO.getTrace());
        assertEquals(fieldErrors, errorDTO.getFieldErrors());
    }

    @Test
    public void testErrorDTOFieldErrorsNotNull() {
        ErrorDTO errorDTO = new ErrorDTO(400, "Bad Request", LocalDateTime.now(), "/api/test", null);
        assertTrue(errorDTO.getFieldErrors() != null);
    }
}
