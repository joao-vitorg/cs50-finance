package com.example.backend.services;

import com.example.backend.models.Transaction;
import com.example.backend.models.dto.TransactionDTO;
import com.example.backend.models.mapper.TransactionMapper;
import com.example.backend.repositories.TransactionRepository;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Service
public class TransactionService {
    private final TransactionRepository repository;
    private final TransactionMapper transactionMapper;

    public TransactionService(TransactionRepository repository,
                              TransactionMapper transactionMapper) {
        this.repository = repository;
        this.transactionMapper = transactionMapper;
    }

    public void buy(Transaction transaction) {
        System.out.println(transaction.getClient().getBalance());
    }

    public TransactionDTO findByID(Integer id) {
        return transactionMapper.toDto(repository.findById(id).orElseThrow());
    }

    public List<TransactionDTO> findAll() {
        return repository.findAll().stream().map(transactionMapper::toDto).collect(Collectors.toList());
    }

    public TransactionDTO save(TransactionDTO entityDTO) {
        Transaction entity = transactionMapper.toEntity(entityDTO);

        buy(entity);

        return transactionMapper.toDto(repository.save(entity));
    }

    public void deleteByID(Integer id) {
        repository.deleteById(id);
    }
}
