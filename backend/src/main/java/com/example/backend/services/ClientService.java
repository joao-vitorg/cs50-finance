package com.example.backend.services;

import com.example.backend.models.Client;
import com.example.backend.models.ClientStock;
import com.example.backend.models.dto.ClientDto;
import com.example.backend.models.dto.ClientVo;
import com.example.backend.models.mapper.EntityMapper;
import com.example.backend.repositories.ClientRepository;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Service
public class ClientService {
    private final ClientRepository repository;
    private final StockService stockService;
    private final EntityMapper mapper;

    public ClientService(ClientRepository repository,
                         StockService stockService, EntityMapper mapper) {
        this.repository = repository;
        this.stockService = stockService;
        this.mapper = mapper;
    }

    public void deposit(Client client, BigDecimal amount) {
        client.setBalance(client.getBalance().add(amount));
    }

    public void withdraw(Client client, BigDecimal amount) {
        if (client.getBalance().compareTo(amount) < 0) throw new Error("Insufficient funds.");
        client.setBalance(client.getBalance().subtract(amount));
    }

    public void updateVirtualBalance(Client client) {
        BigDecimal virtualBalance = BigDecimal.ZERO;
        for (ClientStock transaction : client.getClientStocks()) {
            BigDecimal stockPrice = transaction.getStock().getPrice();
            BigDecimal amount = BigDecimal.valueOf(transaction.getShares());
            virtualBalance = virtualBalance.add(stockPrice.multiply(amount));
        }
        client.setVirtualBalance(virtualBalance);
    }

    public ClientVo findByID(Integer id) {
        return mapper.map(repository.findById(id).orElseThrow());
    }

    public List<ClientVo> findAll() {
        return repository.findAll().stream().map(mapper::map).collect(Collectors.toList());
    }

    public ClientVo save(ClientDto entityDTO) {
        Client entity = mapper.map(entityDTO);
        return mapper.map(repository.save(entity));
    }

    public void deleteByID(Integer id) {
        repository.deleteById(id);
    }
}
