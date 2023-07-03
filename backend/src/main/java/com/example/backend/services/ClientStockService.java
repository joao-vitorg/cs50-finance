package com.example.backend.services;

import com.example.backend.models.dto.ClientStockDto;
import com.example.backend.models.mapper.EntityMapper;
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
    private final EntityMapper mapper;

    public ClientStockService(ClientStockRepository repository,
                              ClientService clientService, EntityMapper mapper) {
        this.repository = repository;
        this.clientService = clientService;
        this.mapper = mapper;
    }

//    public void buy(ClientStockDto clientStockDto) {
//        ClientStock entity = mapper.map(clientStockDto);
//        entity.setShares(entity.getShares() + clientStock.getShares());
//        entity.setTotal(entity.getTotal().add(clientStock.getTotal()));
//        repository.save(entity);
//    }

    public ClientStockDto findByID(Integer id) {
        return mapper.map(repository.findById(id).orElseThrow());
    }

    public List<ClientStockDto> findAll() {
        return repository.findAll().stream().map(mapper::map).collect(Collectors.toList());
    }
}
