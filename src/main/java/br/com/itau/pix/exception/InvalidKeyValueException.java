package br.com.itau.pix.exception;

public class InvalidKeyValueException extends RuntimeException{

    public InvalidKeyValueException(final String message) {
        super(message);
    }
}
