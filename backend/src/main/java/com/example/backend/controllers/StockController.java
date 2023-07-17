package com.example.backend.controllers;

import com.example.backend.models.dto.StockDto;
import com.example.backend.models.vo.StockVo;
import com.example.backend.services.StockService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Min;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/v1/stock")
@Tag(name = "Stock", description = "Stock API")
@Validated
public class StockController {
    private final StockService service;

    public StockController(StockService service) {
        this.service = service;
    }

    @GetMapping
    public Page<StockVo> findAllStocks(@ParameterObject Pageable pageable) {
        return service.findAll(pageable);
    }

    @GetMapping("/{id}")
    public StockVo findStockById(@PathVariable @Min(1) Long id) {
        return service.findByID(id);
    }

    @PostMapping()
    public StockVo insertStock(@RequestBody StockDto stockDto) {
        return service.save(stockDto);
    }
}
