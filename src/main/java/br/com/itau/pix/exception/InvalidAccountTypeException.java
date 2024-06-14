package br.com.itau.pix.exception;

public class InvalidAccountTypeException extends RuntimeException{

    public InvalidAccountTypeException(final String message) {
        super(message);
    }
}