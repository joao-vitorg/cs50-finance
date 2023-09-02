package com.example.backend.services;

import com.example.backend.models.Stock;
import com.example.backend.models.StockHistory;
import com.example.backend.models.vo.StockHistoryVo;
import com.example.backend.repositories.StockHistoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class StockHistoryService {
    private final StockHistoryRepository repository;

    public StockHistoryService(StockHistoryRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public List<StockHistoryVo> findByStockId(Long id) {
        return repository.findByStockId(id);
    }

    public void save(Stock stock, BigDecimal price) {
        repository.save(new StockHistory(stock, price));
    }
}
