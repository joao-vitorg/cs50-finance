package com.example.backend.controllers;

import com.example.backend.models.dto.ClientStockDto;
import com.example.backend.services.ClientStockService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/client")
@Tag(name = "Client Stock", description = "Client Stock API")
public class ClientStockController {
    private final ClientStockService service;

    public ClientStockController(ClientStockService service) {
        this.service = service;
    }

    @GetMapping("/stock")
    public List<ClientStockDto> findStock() {
        return service.findAll();
    }

    @GetMapping("/{id}/stock")
    public List<ClientStockDto> findStockById(@PathVariable Integer id) {
        return service.findByClientID(id);
    }
}
