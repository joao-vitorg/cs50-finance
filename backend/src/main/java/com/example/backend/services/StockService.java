package com.example.backend.services;

import com.example.backend.models.StockHistory;
import com.example.backend.models.dto.StockDto;
import com.example.backend.models.mapper.EntityMapper;
import com.example.backend.models.vo.StockVo;
import com.example.backend.repositories.StockHistoryRepository;
import com.example.backend.repositories.StockRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Random;

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
    public StockVo findByID(Long id) {
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
    @Scheduled(fixedDelay = 60000)
    public void updateStocks() {
        Random random = new Random();
        repository.findAll().forEach(stock -> {
            BigDecimal factor = BigDecimal.valueOf(1 + random.nextDouble(0.06) - 0.03);
            BigDecimal newPrice = stock.getPrice().multiply(factor);

            BigDecimal minimum = BigDecimal.valueOf(0.01);
            if (newPrice.compareTo(minimum) < 0) newPrice = minimum;

            stock.setPrice(newPrice);
            stockHistoryRepository.save(new StockHistory(stock, newPrice));
        });
    }
}
