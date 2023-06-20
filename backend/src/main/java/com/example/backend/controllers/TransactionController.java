package com.example.backend.controllers;

import com.example.backend.models.Transaction;
import com.example.backend.services.TransactionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/transaction")
public class TransactionController {
    private final TransactionService service;

    public TransactionController(TransactionService service) {
        this.service = service;
    }

    @GetMapping
    public List<Transaction> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Transaction findById(@PathVariable Integer id) {
        return service.findByID(id);
    }

    @PostMapping
    public void insert(@RequestBody Transaction user) {
        service.save(user);
    }

    @PutMapping
    public void update(@RequestBody Transaction user) {
        service.save(user);
    }
}
