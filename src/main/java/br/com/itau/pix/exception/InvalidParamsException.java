package br.com.itau.pix.exception;

public class InvalidParamsException extends RuntimeException{

    public InvalidParamsException(final String message) {
        super(message);
    }
}