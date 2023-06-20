package com.example.backend.services;

import com.example.backend.models.Transaction;
import com.example.backend.repositories.TransactionRepository;
import lombok.Getter;
import org.springframework.stereotype.Service;

@Getter
@Service
public class TransactionService implements BaseService<Transaction> {
    private final TransactionRepository repository;

    public TransactionService(TransactionRepository repository) {
        this.repository = repository;
    }
}
