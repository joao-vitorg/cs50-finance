package com.example.backend.services;

import com.example.backend.models.Transaction;
import com.example.backend.models.dto.TransactionDto;
import com.example.backend.models.mapper.EntityMapper;
import com.example.backend.repositories.TransactionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionService {
    private final TransactionRepository repository;
    private final ClientStockService clientStockService;
    private final EntityMapper mapper;

    public TransactionService(TransactionRepository repository, ClientStockService clientStockService, EntityMapper mapper) {
        this.repository = repository;
        this.clientStockService = clientStockService;
        this.mapper = mapper;
    }

    @Transactional(readOnly = true)
    public TransactionDto findByID(Integer id) {
        return mapper.map(repository.findById(id).orElseThrow());
    }

    @Transactional(readOnly = true)
    public List<TransactionDto> findAll() {
        return repository.findAll().stream().map(mapper::map).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<TransactionDto> findByClientId(Integer id) {
        return repository.findByClientId(id).stream().map(mapper::map).collect(Collectors.toList());
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public TransactionDto save(TransactionDto entityDTO) {
        Transaction transaction = mapper.map(entityDTO);

        BigDecimal stockPrice = transaction.getStock().getPrice();
        BigDecimal amount = BigDecimal.valueOf(transaction.getShares());
        transaction.setTotal(stockPrice.multiply(amount).negate());

        clientStockService.transact(transaction);

        return mapper.map(repository.save(transaction));
    }
}
