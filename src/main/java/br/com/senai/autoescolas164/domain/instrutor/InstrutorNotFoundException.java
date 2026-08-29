package br.com.senai.autoescolas164.domain.instrutor;

public class InstrutorNotFoundException extends RuntimeException {
    public InstrutorNotFoundException(String message) {
        super(message);
    }
}
