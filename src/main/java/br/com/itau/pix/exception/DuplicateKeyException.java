package br.com.itau.pix.exception;

public class DuplicateKeyException extends RuntimeException{

    public DuplicateKeyException(final String message) {
        super(message);
    }
}
