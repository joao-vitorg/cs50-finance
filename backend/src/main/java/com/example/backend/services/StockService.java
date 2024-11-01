package com.example.backend.services;

import com.example.backend.infra.exceptions.DuplicatedEntryException;
import com.example.backend.infra.mapper.EntityMapper;
import com.example.backend.models.Stock;
import com.example.backend.models.dto.StockDto;
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
        if (repository.existsBySymbol(stock.symbol())) {
            throw new DuplicatedEntryException();
        }

        Stock saved = repository.save(mapper.map(stock));
        return mapper.map(saved);
    }

    @Transactional
    @Scheduled(fixedDelay = 1, timeUnit = TimeUnit.MINUTES)
    public void updateStocks() {
        repository.findAll().forEach(stock -> {
            BigDecimal newPrice = calculateNewPrice(stock);
            stock.setPrice(newPrice);
            stockHistoryService.save(stock, newPrice);
        });
    }

    private BigDecimal calculateNewPrice(Stock stock) {
        double factor = 1 + new Random().nextDouble(0.06) - 0.03;
        BigDecimal newPrice = stock.getPrice().multiply(BigDecimal.valueOf(factor));
        return newPrice.compareTo(BigDecimal.valueOf(0.01)) < 0 ? BigDecimal.valueOf(0.01) : newPrice;
    }
}
