package com.example.backend.infra.exceptions;

public class ClientDontHaveStockException extends RuntimeException {
    public ClientDontHaveStockException() {
        super("Cliente don't have Stock.");
    }
}
