package com.example.backend.services;

import com.example.backend.models.Transaction;
import com.example.backend.models.dto.TransactionDto;
import com.example.backend.models.mapper.EntityMapper;
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
    private final EntityMapper mapper;

    public TransactionService(TransactionRepository repository, ClientService clientService, EntityMapper mapper) {
        this.repository = repository;
        this.clientService = clientService;
        this.mapper = mapper;
    }

    public void buy(Transaction transaction) {
        clientService.withdraw(transaction.getClient(), transaction.getTotal());
        clientService.updateVirtualBalance(transaction.getClient());
    }

    public void sell(Transaction transaction) {
        clientService.deposit(transaction.getClient(), transaction.getTotal());
    }

    public TransactionDto findByID(Integer id) {
        return mapper.map(repository.findById(id).orElseThrow());
    }

    public List<TransactionDto> findAll() {
        return repository.findAll().stream().map(mapper::map).collect(Collectors.toList());
    }

    public TransactionDto save(TransactionDto entityDTO) {
        Transaction transaction = mapper.map(entityDTO);

        BigDecimal stockPrice = transaction.getStock().getPrice();
        BigDecimal amount = BigDecimal.valueOf(transaction.getShares());
        transaction.setTotal(stockPrice.multiply(amount));

        if (transaction.getShares() > 0) buy(transaction);
        else if (transaction.getShares() < 0) sell(transaction);
        else throw new Error("Shares cannot be 0.");

        return mapper.map(repository.save(transaction));
    }
}
