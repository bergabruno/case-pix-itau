package br.com.itau.pix.exception;

public class InactiveKeyException extends RuntimeException{

    public InactiveKeyException(final String message) {
        super(message);
    }
}
