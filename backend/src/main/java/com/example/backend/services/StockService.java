package com.example.backend.services;

import com.example.backend.models.Stock;
import com.example.backend.repositories.StockRepository;
import lombok.Getter;
import org.springframework.stereotype.Service;

@Getter
@Service
public class StockService implements BaseService<Stock> {
    private final StockRepository repository;

    public StockService(StockRepository repository) {
        this.repository = repository;
    }
}
