package br.com.itau.pix.exception;

public class InvalidKeyException extends RuntimeException{

    public InvalidKeyException(final String message) {
        super(message);
    }
}
