package com.example.backend.services;

import com.example.backend.models.Stock;
import com.example.backend.models.StockHistory;
import com.example.backend.models.mapper.EntityMapper;
import com.example.backend.models.vo.StockHistoryVo;
import com.example.backend.repositories.StockHistoryRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class StockHistoryService {
    private final StockHistoryRepository repository;
    private final EntityMapper mapper;

    public StockHistoryService(StockHistoryRepository repository, EntityMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Transactional(readOnly = true)
    public Page<StockHistoryVo> findAll(Pageable pageable) {
        return repository.findAll(pageable).map(mapper::map);
    }

    @Transactional(readOnly = true)
    public Page<StockHistoryVo> findByStockId(Long id, Pageable pageable) {
        return repository.findByStockId(id, pageable).map(mapper::map);
    }

    public void save(Stock stock, BigDecimal price) {
        repository.save(new StockHistory(stock, price));
    }
}
