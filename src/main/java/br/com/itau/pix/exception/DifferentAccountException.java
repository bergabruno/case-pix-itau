package br.com.itau.pix.exception;

public class DifferentAccountException extends RuntimeException{

    public DifferentAccountException(final String message) {
        super(message);
    }
}
