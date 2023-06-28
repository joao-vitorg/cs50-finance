package com.example.backend.controllers;

import com.example.backend.models.dto.StockVo;
import com.example.backend.services.StockService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/stock")
public class StockController {
    private final StockService service;

    public StockController(StockService service) {
        this.service = service;
    }

    @GetMapping
    public List<StockVo> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public StockVo findById(@PathVariable Integer id) {
        return service.findByID(id);
    }
}
