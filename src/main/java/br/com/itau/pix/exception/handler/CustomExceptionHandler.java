package br.com.itau.pix.exception.handler;

import br.com.itau.pix.dto.error.ErrorDTO;
import br.com.itau.pix.dto.error.FieldErrorDTO;
import br.com.itau.pix.exception.AlreadyInactiveException;
import br.com.itau.pix.exception.DuplicateKeyException;
import br.com.itau.pix.exception.InvalidKeyException;
import br.com.itau.pix.exception.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public final ResponseEntity<ErrorDTO> handleResourceNotFoundException(ResourceNotFoundException ex,  HttpServletRequest request) {
        ErrorDTO errorDTO = new ErrorDTO(HttpStatus.NOT_FOUND.value(), ex.getMessage(), LocalDateTime.now(), request.getRequestURI(), null);
        return new ResponseEntity<>(errorDTO, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DuplicateKeyException.class)
    public final ResponseEntity<ErrorDTO> handleDuplicateKeyException(DuplicateKeyException ex,  HttpServletRequest request) {
        ErrorDTO errorDTO = new ErrorDTO(HttpStatus.UNPROCESSABLE_ENTITY.value(), ex.getMessage(), LocalDateTime.now(),request.getRequestURI(), null);
        return new ResponseEntity<>(errorDTO, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(InvalidKeyException.class)
    public final ResponseEntity<ErrorDTO> handleInvalidKeyException(InvalidKeyException ex,  HttpServletRequest request) {
        ErrorDTO errorDTO = new ErrorDTO(HttpStatus.UNPROCESSABLE_ENTITY.value(), ex.getMessage(), LocalDateTime.now(),request.getRequestURI(), null);
        return new ResponseEntity<>(errorDTO, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(AlreadyInactiveException.class)
    public final ResponseEntity<ErrorDTO> handleAlreadyInactiveException(AlreadyInactiveException ex,  HttpServletRequest request) {
        ErrorDTO errorDTO = new ErrorDTO(HttpStatus.UNPROCESSABLE_ENTITY.value(), ex.getMessage(), LocalDateTime.now(),request.getRequestURI(), null);
        return new ResponseEntity<>(errorDTO, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public final ResponseEntity<ErrorDTO> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex, HttpServletRequest request) {
        List<FieldErrorDTO> fieldErrors = ex.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> new FieldErrorDTO(fieldError.getField(), fieldError.getDefaultMessage()))
                .collect(Collectors.toList());

        ErrorDTO errorDTO = new ErrorDTO(HttpStatus.BAD_REQUEST.value(), "Validation failed", LocalDateTime.now(), request.getRequestURI(), fieldErrors);

        return new ResponseEntity<>(errorDTO, HttpStatus.BAD_REQUEST);
    }
}
