package com.example.backend.services;

import com.example.backend.models.dto.StockHistoryDto;
import com.example.backend.models.mapper.EntityMapper;
import com.example.backend.repositories.StockHistoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StockHistoryService {
    private final StockHistoryRepository repository;
    private final EntityMapper mapper;

    public StockHistoryService(StockHistoryRepository repository,
                               EntityMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public StockHistoryDto findByID(Integer id) {
        return mapper.map(repository.findById(id).orElseThrow());
    }

    public List<StockHistoryDto> findAll() {
        return repository.findAll().stream().map(mapper::map).collect(Collectors.toList());
    }

    public void deleteByID(Integer id) {
        repository.deleteById(id);
    }
}
