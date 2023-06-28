package com.example.backend.controllers;

import com.example.backend.models.dto.TransactionDTO;
import com.example.backend.models.dto.TransactionVo;
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
    public List<TransactionVo> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public TransactionVo findById(@PathVariable Integer id) {
        return service.findByID(id);
    }

    @PostMapping
    public TransactionVo insert(@RequestBody TransactionDTO transaction) {
        return service.save(transaction);
    }
}
