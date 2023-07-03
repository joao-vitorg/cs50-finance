package com.example.backend.services;

import com.example.backend.models.dto.StockDto;
import com.example.backend.models.mapper.EntityMapper;
import com.example.backend.repositories.StockRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StockService {
    private final StockRepository repository;
    private final EntityMapper mapper;

    public StockService(StockRepository repository,
                        EntityMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public StockDto findByID(Integer id) {
        return mapper.map(repository.findById(id).orElseThrow());
    }

    public List<StockDto> findAll() {
        return repository.findAll().stream().map(mapper::map).collect(Collectors.toList());
    }
}
