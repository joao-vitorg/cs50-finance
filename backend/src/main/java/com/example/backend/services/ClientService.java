package com.example.backend.services;

import com.example.backend.models.Client;
import com.example.backend.models.dto.ClientDto;
import com.example.backend.models.dto.ClientVo;
import com.example.backend.models.mapper.EntityMapper;
import com.example.backend.repositories.ClientRepository;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Service
public class ClientService {
    private final ClientRepository repository;
    private final StockService stockService;
    private final EntityMapper mapper;

    public ClientService(ClientRepository repository, StockService stockService, EntityMapper mapper) {
        this.repository = repository;
        this.stockService = stockService;
        this.mapper = mapper;
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
