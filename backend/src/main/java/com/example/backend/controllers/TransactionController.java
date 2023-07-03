package com.example.backend.controllers;

import com.example.backend.models.dto.TransactionDto;
import com.example.backend.services.TransactionService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/transaction")
@Tag(name = "Transaction", description = "Transaction API")
public class TransactionController {
    private final TransactionService service;

    public TransactionController(TransactionService service) {
        this.service = service;
    }

    @GetMapping
    public List<TransactionDto> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public TransactionDto findById(@PathVariable Integer id) {
        return service.findByID(id);
    }

    @PostMapping
    public TransactionDto insert(@RequestBody TransactionDto transaction) {
        return service.save(transaction);
    }
}
