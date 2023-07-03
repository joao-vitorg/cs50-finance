package com.example.backend.services;

import com.example.backend.models.ClientStock;
import com.example.backend.models.Transaction;
import com.example.backend.models.dto.ClientStockDto;
import com.example.backend.models.mapper.EntityMapper;
import com.example.backend.repositories.ClientStockRepository;
import lombok.Getter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Getter
@Service
public class ClientStockService {
    private final ClientStockRepository repository;
    private final ClientService clientService;
    private final EntityMapper mapper;

    public ClientStockService(ClientStockRepository repository, ClientService clientService, EntityMapper mapper) {
        this.repository = repository;
        this.clientService = clientService;
        this.mapper = mapper;
    }

    private void buy(Transaction transaction) {
        transaction.getClient().transactBalance(transaction.getTotal());

        ClientStock clientStock = save(transaction.getClient().getId(), transaction.getStock().getId());

        clientStock.transactShares(transaction.getShares());
    }

    private void sell(Transaction transaction) {
        ClientStock clientStock = findByClientIdAndStockId(transaction.getClient().getId(), transaction.getStock().getId())
                .orElseThrow(() -> new Error("Cliente não possui a Stock,"));

        clientStock.transactShares(transaction.getShares());

        transaction.getClient().transactBalance(transaction.getTotal());

        if (clientStock.getShares() == 0) repository.delete(clientStock);
    }

    @Transactional
    public void transact(Transaction transaction) {
        if (transaction.getShares() > 0) buy(transaction);
        else if (transaction.getShares() < 0) sell(transaction);
        else throw new Error("Shares cannot be 0.");
    }

    private Optional<ClientStock> findByClientIdAndStockId(Integer clientId, Integer stockId) {
        return repository.findByClientIdAndStockId(clientId, stockId);
    }

    public ClientStockDto findByID(Integer id) {
        return mapper.map(repository.findById(id).orElseThrow());
    }

    public List<ClientStockDto> findByClientID(Integer id) {
        return repository.findByClientId(id).stream().map(mapper::map).collect(Collectors.toList());
    }

    public List<ClientStockDto> findAll() {
        return repository.findAll().stream().map(mapper::map).collect(Collectors.toList());
    }

    public ClientStock save(Integer clientId, Integer stockId) {
        ClientStock clientStock = findByClientIdAndStockId(clientId, stockId)
                .orElseGet(() -> mapper.toClientStock(clientId, stockId));

        return repository.save(clientStock);
    }
}
