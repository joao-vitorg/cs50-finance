package com.example.backend.controllers;

import com.example.backend.models.Client;
import com.example.backend.services.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/user")
public class UserController {
    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping
    public List<Client> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Client findById(@PathVariable Integer id) {
        return service.findByID(id);
    }

    @PostMapping
    public void insert(@RequestBody Client client) {
        service.save(client);
    }

    @PutMapping
    public void update(@RequestBody Client client) {
        service.save(client);
    }
}
