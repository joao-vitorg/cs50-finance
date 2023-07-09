package com.example.backend.services;

import com.example.backend.models.Client;
import com.example.backend.models.ClientStock;
import com.example.backend.models.Stock;
import com.example.backend.models.Transaction;
import com.example.backend.models.dto.ClientStockDto;
import com.example.backend.models.mapper.EntityMapper;
import com.example.backend.repositories.ClientStockRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClientStockService {
    private final ClientStockRepository repository;
    private final EntityMapper mapper;

    public ClientStockService(ClientStockRepository repository, EntityMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Transactional(readOnly = true)
    public List<ClientStockDto> findByClientID(Integer id) {
        return repository.findByClientId(id).stream().map(mapper::map).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<ClientStockDto> findAll() {
        return repository.findAll().stream().map(mapper::map).collect(Collectors.toList());
    }

    private Optional<ClientStock> findByClientAndStock(Client client, Stock stock) {
        return repository.findByClientAndStock(client, stock);
    }

    protected void transact(Transaction transaction) {
        if (transaction.getShares() > 0) buy(transaction);
        else if (transaction.getShares() < 0) sell(transaction);
        else throw new Error("Shares cannot be 0.");
    }

    private void buy(Transaction transaction) {
        Client client = transaction.getClient();
        Stock stock = transaction.getStock();
        ClientStock clientStock = findByClientAndStock(client, stock)
                .orElseGet(() -> repository.save(new ClientStock(client, stock)));

        clientStock.getClient().transactBalance(transaction.getTotal());
        clientStock.transactShares(transaction.getShares());
    }

    private void sell(Transaction transaction) {
        ClientStock clientStock = findByClientAndStock(transaction.getClient(), transaction.getStock())
                .orElseThrow(() -> new Error("Cliente don't have Stock."));

        clientStock.transactShares(transaction.getShares());
        clientStock.getClient().transactBalance(transaction.getTotal());
        if (clientStock.getShares() == 0) repository.delete(clientStock);
    }
}
