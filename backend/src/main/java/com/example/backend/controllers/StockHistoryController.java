package com.example.backend.controllers;

import com.example.backend.models.vo.StockHistoryVo;
import com.example.backend.services.StockHistoryService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Min;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public Page<StockHistoryVo> findStockHistoryByStockId(@PathVariable @Min(1) Long id, @ParameterObject Pageable pageable) {
        return service.findByStockId(id, pageable);
    }

    @GetMapping("/history")
    public Page<StockHistoryVo> findAllStockHistory(@ParameterObject Pageable pageable) {
        return service.findAll(pageable);
    }
}
