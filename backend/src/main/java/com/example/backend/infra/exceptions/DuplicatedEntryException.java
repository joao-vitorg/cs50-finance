package com.example.backend.infra.exceptions;

public class DuplicatedEntryException extends RuntimeException {
    public DuplicatedEntryException() {
        super("Entry already exists.");
    }
}
