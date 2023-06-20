package com.example.backend.services;

import com.example.backend.models.StockHistory;
import com.example.backend.repositories.StockHistoryRepository;
import lombok.Getter;
import org.springframework.stereotype.Service;

@Getter
@Service
public class StockHistoryService implements BaseService<StockHistory> {
    private final StockHistoryRepository repository;

    public StockHistoryService(StockHistoryRepository repository) {
        this.repository = repository;
    }
}
