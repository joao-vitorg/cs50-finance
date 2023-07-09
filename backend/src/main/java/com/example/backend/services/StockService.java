package com.example.backend.services;

import com.example.backend.models.StockHistory;
import com.example.backend.models.dto.StockDto;
import com.example.backend.models.mapper.EntityMapper;
import com.example.backend.repositories.StockHistoryRepository;
import com.example.backend.repositories.StockRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class StockService {
    private final StockRepository repository;
    private final EntityMapper mapper;
    private final StockHistoryRepository stockHistoryRepository;

    public StockService(StockRepository repository, StockHistoryRepository stockHistoryRepository, EntityMapper mapper) {
        this.repository = repository;
        this.stockHistoryRepository = stockHistoryRepository;
        this.mapper = mapper;
    }

    @Transactional(readOnly = true)
    public StockDto findByID(Integer id) {
        return mapper.map(repository.findById(id).orElseThrow());
    }

    @Transactional(readOnly = true)
    public List<StockDto> findAll() {
        return repository.findAll().stream().map(mapper::map).collect(Collectors.toList());
    }

    @Transactional()
    @Scheduled(fixedDelay = 60000)
    public void updateStocks() {
        Random random = new Random();
        repository.findAll().forEach(stock -> {
            BigDecimal factor = BigDecimal.valueOf(1 + random.nextDouble(0.06) - 0.03);
            BigDecimal newPrice = stock.getPrice().multiply(factor);

            if (newPrice.compareTo(BigDecimal.ZERO) < 0) newPrice = BigDecimal.ZERO;

            stock.setPrice(newPrice);
            stockHistoryRepository.save(new StockHistory(stock, newPrice));
        });
    }
}
