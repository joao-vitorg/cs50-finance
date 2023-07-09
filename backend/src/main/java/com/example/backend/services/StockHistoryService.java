package com.example.backend.services;

import com.example.backend.models.Stock;
import com.example.backend.models.StockHistory;
import com.example.backend.models.dto.StockHistoryDto;
import com.example.backend.models.mapper.EntityMapper;
import com.example.backend.repositories.StockHistoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StockHistoryService {
    private final StockHistoryRepository repository;
    private final EntityMapper mapper;

    public StockHistoryService(StockHistoryRepository repository, EntityMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Transactional(readOnly = true)
    public List<StockHistoryDto> findAll() {
        return repository.findAll().stream().map(mapper::map).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<StockHistoryDto> findByStockId(Integer id) {
        return repository.findByStockId(id).stream().map(mapper::map).collect(Collectors.toList());
    }

    public void save(Stock stock, BigDecimal price) {
        repository.save(new StockHistory(stock, price));
    }
}
