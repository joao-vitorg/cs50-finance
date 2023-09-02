package com.example.backend.services;

import com.example.backend.infra.mapper.EntityMapper;
import com.example.backend.models.Transaction;
import com.example.backend.models.dto.TransactionDto;
import com.example.backend.models.vo.TransactionVo;
import com.example.backend.repositories.TransactionRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

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
    public Page<TransactionVo> findAll(Pageable pageable) {
        return repository.findAll(pageable).map(mapper::map);
    }

    @Transactional(readOnly = true)
    public Page<TransactionVo> findByClientId(Long id, Pageable pageable) {
        return repository.findByClientId(id, pageable).map(mapper::map);
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public TransactionVo save(TransactionDto entityDTO) {
        Transaction transaction = mapper.map(entityDTO);

        BigDecimal stockPrice = transaction.getStock().getPrice();
        BigDecimal amount = BigDecimal.valueOf(transaction.getShares());
        transaction.setTotal(stockPrice.multiply(amount).negate());

        clientStockService.transact(transaction);

        return mapper.map(repository.save(transaction));
    }
}
