package com.example.backend.controllers;

import com.example.backend.models.dto.StockDto;
import com.example.backend.models.dto.StockHistoryDto;
import com.example.backend.services.StockHistoryService;
import com.example.backend.services.StockService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/stock")
@Tag(name = "Stock", description = "Stock API")
public class StockController {
    private final StockService service;
    private final StockHistoryService stockHistoryService;

    public StockController(StockService service, StockHistoryService stockHistoryService) {
        this.service = service;
        this.stockHistoryService = stockHistoryService;
    }

    @GetMapping
    public List<StockDto> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public StockDto findById(@PathVariable Integer id) {
        return service.findByID(id);
    }

    @GetMapping("/{id}/history")
    public List<StockHistoryDto> findByStockId(@PathVariable Integer id) {
        return stockHistoryService.findByStockId(id);
    }

    @GetMapping("/history")
    public List<StockHistoryDto> findAllStockHistory() {
        return stockHistoryService.findAll();
    }
}
