package com.example.backend.controllers;

import com.example.backend.models.dto.ClientDto;
import com.example.backend.models.dto.ClientStockDto;
import com.example.backend.models.dto.ClientVo;
import com.example.backend.services.ClientService;
import com.example.backend.services.ClientStockService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/client")
@Tag(name = "Client", description = "Client API")
public class ClientController {
    private final ClientService service;
    private final ClientStockService clientStockService;

    public ClientController(ClientService service, ClientStockService clientStockService) {
        this.service = service;
        this.clientStockService = clientStockService;
    }

    @GetMapping
    public List<ClientVo> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ClientVo findById(@PathVariable Integer id) {
        return service.findByID(id);
    }

    @GetMapping("/{id}/stock")
    public List<ClientStockDto> findStockById(@PathVariable Integer id) {
        return clientStockService.findByClientID(id);
    }

    @GetMapping("/stock")
    public List<ClientStockDto> findStock() {
        return clientStockService.findAll();
    }

    @PostMapping
    public ClientVo insert(@RequestBody ClientDto user) {
        return service.save(user);
    }

    @PutMapping
    public ClientVo update(@RequestBody ClientDto user) {
        return service.save(user);
    }
}
