package com.example.backend.controllers;

import com.example.backend.models.dto.TransactionDto;
import com.example.backend.models.vo.TransactionVo;
import com.example.backend.services.TransactionService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/v1/transaction")
@Tag(name = "Transaction", description = "Transaction API")
@Validated
public class TransactionController {
    private final TransactionService service;

    public TransactionController(TransactionService service) {
        this.service = service;
    }

    @GetMapping
    public Page<TransactionVo> findAllTransactions(@ParameterObject Pageable pageable) {
        return service.findAll(pageable);
    }

    @GetMapping("/{id}")
    public TransactionVo findTransactionById(@PathVariable @Min(1) Long id) {
        return service.findByID(id);
    }

    @GetMapping("/client/{id}")
    public Page<TransactionVo> findTransactionByClientId(@PathVariable @Min(1) Long id, @ParameterObject Pageable pageable) {
        return service.findByClientId(id, pageable);
    }

    @PostMapping()
    public TransactionVo insertTransaction(@Valid @RequestBody TransactionDto transaction) {
        return service.save(transaction);
    }
}
