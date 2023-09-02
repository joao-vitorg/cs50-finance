package com.example.backend.controllers;

import com.example.backend.models.vo.StockHistoryVo;
import com.example.backend.services.StockHistoryService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Min;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/stock")
@Tag(name = "Stock History", description = "Stock History API")
@Validated
public class StockHistoryController {
    private final StockHistoryService service;

    public StockHistoryController(StockHistoryService service) {
        this.service = service;
    }

    @GetMapping("/{id}/history")
    public List<StockHistoryVo> findStockHistoryByStockId(@PathVariable @Min(1) Long id) {
        return service.findByStockId(id);
    }
}
