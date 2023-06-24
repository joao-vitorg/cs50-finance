package com.example.backend.controllers;

import com.example.backend.models.dto.ClientDTO;
import com.example.backend.services.ClientService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/user")
public class UserController {
    private final ClientService service;

    public UserController(ClientService service) {
        this.service = service;
    }

    @GetMapping
    public List<ClientDTO> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ClientDTO findById(@PathVariable Integer id) {
        return service.findByID(id);
    }

    @PostMapping
    public void insert(@RequestBody ClientDTO user) {
        service.save(user);
    }

    @PutMapping
    public void update(@RequestBody ClientDTO user) {
        service.save(user);
    }
}
