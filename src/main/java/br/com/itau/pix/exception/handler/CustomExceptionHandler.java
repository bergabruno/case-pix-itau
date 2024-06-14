package br.com.itau.pix.exception.handler;

import br.com.itau.pix.dto.error.ErrorDTO;
import br.com.itau.pix.dto.error.FieldErrorDTO;
import br.com.itau.pix.exception.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
@Slf4j
public class CustomExceptionHandler {

    private static void log(ErrorDTO errorDTO) {
        log.error("Error in Application: {}", errorDTO);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public final ResponseEntity<ErrorDTO> handleResourceNotFoundException(ResourceNotFoundException ex,  HttpServletRequest request) {
        ErrorDTO errorDTO = new ErrorDTO(HttpStatus.NOT_FOUND.value(), ex.getMessage(), LocalDateTime.now(), request.getRequestURI(), List.of(ex.getFieldErrorDTO()));
        log(errorDTO);
        return new ResponseEntity<>(errorDTO, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(ValidationException.class)
    public final ResponseEntity<ErrorDTO> handleValidationException(ValidationException ex,  HttpServletRequest request) {
        ErrorDTO errorDTO = new ErrorDTO(HttpStatus.UNPROCESSABLE_ENTITY.value(), ex.getMessage(), LocalDateTime.now(),request.getRequestURI(), ex.getFieldErrors());
        log(errorDTO);
        return new ResponseEntity<>(errorDTO, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(InvalidAccountTypeException.class)
    public final ResponseEntity<ErrorDTO> handleInvalidAccountTypeException(InvalidAccountTypeException ex, HttpServletRequest request) {
        ErrorDTO errorDTO = new ErrorDTO(HttpStatus.BAD_REQUEST.value(), ex.getMessage(), LocalDateTime.now(), request.getRequestURI(), List.of());
        log(errorDTO);
        return new ResponseEntity<>(errorDTO, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public final ResponseEntity<ErrorDTO> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex, HttpServletRequest request) {
        List<FieldErrorDTO> fieldErrors = ex.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> new FieldErrorDTO(fieldError.getField(), fieldError.getDefaultMessage()))
                .collect(Collectors.toList());

        ErrorDTO errorDTO = new ErrorDTO(HttpStatus.BAD_REQUEST.value(), "Validation failed", LocalDateTime.now(), request.getRequestURI(), fieldErrors);
        log(errorDTO);
        return new ResponseEntity<>(errorDTO, HttpStatus.BAD_REQUEST);
    }
}
