package br.com.itau.pix.exception;

public class InvalidKeyTypeException extends RuntimeException{

    public InvalidKeyTypeException(final String message) {
        super(message);
    }
}
