package com.example.backend.services;

import com.example.backend.models.dto.StockDto;
import com.example.backend.models.mapper.EntityMapper;
import com.example.backend.models.vo.StockVo;
import com.example.backend.repositories.StockRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
public class StockService {
    private final StockRepository repository;
    private final StockHistoryService stockHistoryService;
    private final EntityMapper mapper;

    public StockService(StockRepository repository, StockHistoryService stockHistoryService, EntityMapper mapper) {
        this.repository = repository;
        this.stockHistoryService = stockHistoryService;
        this.mapper = mapper;
    }

    @Transactional(readOnly = true)
    public StockVo findById(Long id) {
        return mapper.map(repository.findById(id).orElseThrow());
    }

    @Transactional(readOnly = true)
    public Page<StockVo> findAll(Pageable pageable) {
        return repository.findAll(pageable).map(mapper::map);
    }

    @Transactional
    public StockVo save(StockDto stock) {
        return mapper.map(repository.save(mapper.map(stock)));
    }

    @Transactional()
    @Scheduled(fixedDelay = 1, timeUnit = TimeUnit.MINUTES)
    public void updateStocks() {
        Random random = new Random();
        repository.findAll().forEach(stock -> {
            BigDecimal factor = BigDecimal.valueOf(1 + random.nextDouble(0.06) - 0.03);
            BigDecimal newPrice = stock.getPrice().multiply(factor);

            BigDecimal minimum = BigDecimal.valueOf(0.01);
            if (newPrice.compareTo(minimum) < 0) newPrice = minimum;

            stock.setPrice(newPrice);
            stockHistoryService.save(stock, newPrice);
        });
    }
}
