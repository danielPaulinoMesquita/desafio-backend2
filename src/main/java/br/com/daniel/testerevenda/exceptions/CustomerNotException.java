package br.com.daniel.testerevenda.exceptions;

public class CustomerNotException extends RuntimeException {
    public CustomerNotException(String message) {
        super(message);
    }
}
