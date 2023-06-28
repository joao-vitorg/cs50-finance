package com.example.backend.services;

import com.example.backend.models.dto.StockHistoryVo;
import com.example.backend.models.mapper.StockHistoryMapper;
import com.example.backend.repositories.StockHistoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StockHistoryService {
    private final StockHistoryRepository repository;
    private final StockHistoryMapper stockHistoryMapper;

    public StockHistoryService(StockHistoryRepository repository,
                               StockHistoryMapper stockHistoryMapper) {
        this.repository = repository;
        this.stockHistoryMapper = stockHistoryMapper;
    }

    public StockHistoryVo findByID(Integer id) {
        return stockHistoryMapper.toDto(repository.findById(id).orElseThrow());
    }

    public List<StockHistoryVo> findAll() {
        return repository.findAll().stream().map(stockHistoryMapper::toDto).collect(Collectors.toList());
    }

    public void deleteByID(Integer id) {
        repository.deleteById(id);
    }
}
