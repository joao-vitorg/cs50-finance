package com.example.backend.services;

import com.example.backend.models.Stock;
import com.example.backend.models.StockHistory;
import com.example.backend.models.dto.StockHistoryDto;
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
public class StockHistoryService {
    private final StockHistoryRepository repository;
    private final StockService stockService;
    private final EntityMapper mapper;
    private final StockRepository stockRepository;

    public StockHistoryService(StockHistoryRepository repository, StockService stockService, EntityMapper mapper,
                               StockRepository stockRepository) {
        this.repository = repository;
        this.stockService = stockService;
        this.mapper = mapper;
        this.stockRepository = stockRepository;
    }

    public StockHistoryDto findByID(Integer id) {
        return mapper.map(repository.findById(id).orElseThrow());
    }

    public List<StockHistoryDto> findAll() {
        return repository.findAll().stream().map(mapper::map).collect(Collectors.toList());
    }

    public void deleteByID(Integer id) {
        repository.deleteById(id);
    }

    public void save(Stock stock, BigDecimal price) {
        repository.save(new StockHistory(stock, price));
    }

    @Scheduled(fixedDelay = 1000)
    @Transactional
    public void updateStocks() {
        Random random = new Random();
        List<Stock> stocks = stockService.findAll().stream().map(mapper::map).toList();
        for (Stock stock : stocks) {
            double factor = random.nextDouble();

            BigDecimal oldPrice = stock.getPrice();
            BigDecimal newPrice = oldPrice.multiply(BigDecimal.valueOf(factor));

            stock.setPrice(newPrice);

            stockRepository.save(stock);
            repository.save(new StockHistory(mapper.toStock(stock.getId()), newPrice));
        }
    }
}
