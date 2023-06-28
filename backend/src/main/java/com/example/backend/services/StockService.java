package com.example.backend.services;

import com.example.backend.models.Stock;
import com.example.backend.models.dto.StockVo;
import com.example.backend.models.mapper.StockMapper;
import com.example.backend.repositories.StockRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StockService {
    private final StockRepository repository;
    private final StockMapper stockMapper;

    public StockService(StockRepository repository,
                        StockMapper stockMapper) {
        this.repository = repository;
        this.stockMapper = stockMapper;
    }

    public Stock getReference(Integer id) {
        return repository.getReferenceById(id);
    }

    public StockVo findByID(Integer id) {
        return stockMapper.toDto(repository.findById(id).orElseThrow());
    }

    public List<StockVo> findAll() {
        return repository.findAll().stream().map(stockMapper::toDto).collect(Collectors.toList());
    }
}
