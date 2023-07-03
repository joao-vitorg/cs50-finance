package com.example.backend.controllers;

import com.example.backend.models.dto.ClientDto;
import com.example.backend.models.dto.ClientVo;
import com.example.backend.services.ClientService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/client")
@Tag(name = "Client", description = "Client API")
public class ClientController {
    private final ClientService service;

    public ClientController(ClientService service) {
        this.service = service;
    }

    @GetMapping
    public List<ClientVo> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ClientVo findById(@PathVariable Integer id) {
        return service.findByID(id);
    }

    @PostMapping
    public void insert(@RequestBody ClientDto user) {
        service.save(user);
    }

    @PutMapping
    public void update(@RequestBody ClientDto user) {
        service.save(user);
    }
}
