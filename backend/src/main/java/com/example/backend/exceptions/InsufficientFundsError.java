package com.example.backend.exceptions;

public class InsufficientFundsError extends Error {
    public InsufficientFundsError() {
        super("Insufficient funds.");
    }
}
