package com.example.backend.services;

import com.example.backend.exceptions.ClientDontHaveStockError;
import com.example.backend.models.Client;
import com.example.backend.models.ClientStock;
import com.example.backend.models.Stock;
import com.example.backend.models.Transaction;
import com.example.backend.models.mapper.EntityMapper;
import com.example.backend.models.vo.ClientStockVo;
import com.example.backend.repositories.ClientStockRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ClientStockService {
    private final ClientStockRepository repository;
    private final EntityMapper mapper;

    public ClientStockService(ClientStockRepository repository, EntityMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Transactional(readOnly = true)
    public Page<ClientStockVo> findByClientId(Long id, Pageable pageable) {
        return repository.findByClientId(id, pageable).map(mapper::map);
    }

    @Transactional(readOnly = true)
    public Page<ClientStockVo> findAll(Pageable pageable) {
        return repository.findAll(pageable).map(mapper::map);
    }

    private Optional<ClientStock> findByClientAndStock(Client client, Stock stock) {
        return repository.findByClientAndStock(client, stock);
    }

    protected void transact(Transaction transaction) {
        if (transaction.getShares() > 0) buy(transaction);
        else if (transaction.getShares() < 0) sell(transaction);
        else throw new RuntimeException("Shares cannot be 0.");
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
                .orElseThrow(ClientDontHaveStockError::new);

        clientStock.transactShares(transaction.getShares());
        clientStock.getClient().transactBalance(transaction.getTotal());
        if (clientStock.getShares() == 0) repository.delete(clientStock);
    }
}
