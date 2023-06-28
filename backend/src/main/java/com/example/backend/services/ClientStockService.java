package com.example.backend.services;

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
    private final ClientStockMapper clientStockMapper;

    public ClientStockService(ClientStockRepository repository,
                              ClientStockMapper clientStockMapper) {
        this.repository = repository;
        this.clientStockMapper = clientStockMapper;
    }

    public ClientStockVo findByID(Integer id) {
        return clientStockMapper.toDto(repository.findById(id).orElseThrow());
    }

    public List<ClientStockVo> findAll() {
        return repository.findAll().stream().map(clientStockMapper::toDto).collect(Collectors.toList());
    }

    public void deleteByID(Integer id) {
        repository.deleteById(id);
    }
}
