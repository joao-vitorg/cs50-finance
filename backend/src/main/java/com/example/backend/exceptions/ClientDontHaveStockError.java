package com.example.backend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ClientDontHaveStockError extends Error {
    public ClientDontHaveStockError() {
        super("Cliente don't have Stock.");
    }
}
