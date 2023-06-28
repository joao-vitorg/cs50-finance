package com.example.backend.services;

import com.example.backend.models.Transaction;
import com.example.backend.models.dto.TransactionDTO;
import com.example.backend.models.dto.TransactionVo;
import com.example.backend.models.mapper.ClientMapper;
import com.example.backend.models.mapper.TransactionMapper;
import com.example.backend.repositories.TransactionRepository;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Service
public class TransactionService {
    private final TransactionRepository repository;
    private final ClientService clientService;
    private final TransactionMapper transactionMapper;
    private final ClientMapper clientMapper;

    public TransactionService(TransactionRepository repository, ClientService clientService, TransactionMapper transactionMapper,
                              ClientMapper clientMapper) {
        this.repository = repository;
        this.clientService = clientService;
        this.transactionMapper = transactionMapper;
        this.clientMapper = clientMapper;
    }

    public void buy(Transaction transaction) {
        clientService.withdraw(transaction.getClient(), transaction.getPrice());
    }

    public void sell(Transaction transaction) {
        clientService.deposit(transaction.getClient(), transaction.getPrice());
    }

    public TransactionVo findByID(Integer id) {
        return transactionMapper.toDto1(repository.findById(id).orElseThrow());
    }

    public List<TransactionVo> findAll() {
        return repository.findAll().stream().map(transactionMapper::toDto1).collect(Collectors.toList());
    }

    public TransactionVo save(TransactionDTO entityDTO) {
        Transaction transaction = transactionMapper.toEntity(entityDTO);

        BigDecimal stockPrice = transaction.getStock().getPrice();
        BigDecimal amount = BigDecimal.valueOf(transaction.getShares());
        transaction.setPrice(stockPrice.multiply(amount));

        if (transaction.getShares() > 0) buy(transaction);
        else if (transaction.getShares() < 0) sell(transaction);
        else throw new Error();

        return transactionMapper.toDto1(repository.save(transaction));
    }

    public void deleteByID(Integer id) {
        repository.deleteById(id);
    }
}
