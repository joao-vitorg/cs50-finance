package com.example.backend.controllers;

import com.example.backend.models.dto.TransactionDTO;
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
    public List<TransactionDTO> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public TransactionDTO findById(@PathVariable Integer id) {
        return service.findByID(id);
    }

    @PostMapping
    public void insert(@RequestBody TransactionDTO transaction) {
        service.save(transaction);
    }

    @PutMapping
    public void update(@RequestBody TransactionDTO transaction) {
        service.save(transaction);
    }
}
