package br.com.itau.pix.exception;

public class AlreadyInactiveException extends RuntimeException{

    public AlreadyInactiveException(final String message) {
        super(message);
    }
}
