package com.example.backend.exceptions;

public class ClientDontHaveStockError extends Error {
    public ClientDontHaveStockError() {
        super("Cliente don't have Stock.");
    }
}
