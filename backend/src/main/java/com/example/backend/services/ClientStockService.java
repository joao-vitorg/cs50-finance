package com.example.backend.services;

import com.example.backend.models.ClientStock;
import com.example.backend.models.dto.ClientStockDto;
import com.example.backend.models.dto.ClientStockVo;
import com.example.backend.models.mapper.ClientStockMapper;
import com.example.backend.repositories.ClientStockRepository;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Service
public class ClientStockService {
    private final ClientStockRepository repository;
    private final ClientService clientService;
    private final ClientStockMapper clientStockMapper;

    public ClientStockService(ClientStockRepository repository,
                              ClientService clientService, ClientStockMapper clientStockMapper) {
        this.repository = repository;
        this.clientService = clientService;
        this.clientStockMapper = clientStockMapper;
    }

    public void buy(ClientStockDto clientStockDto) {
        ClientStock entity = clientStockMapper.toEntity(clientStockDto);
        entity.setShares(entity.getShares() + clientStock.getShares());
        entity.setTotal(entity.getTotal().add(clientStock.getTotal()));
        repository.save(entity);
    }

    public ClientStockVo findByID(Integer id) {
        return clientStockMapper.toDto(repository.findById(id).orElseThrow());
    }

    public List<ClientStockVo> findAll() {
        return repository.findAll().stream().map(clientStockMapper::toDto).collect(Collectors.toList());
    }
}
