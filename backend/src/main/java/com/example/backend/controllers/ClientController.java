package com.example.backend.controllers;

import com.example.backend.models.dto.ClientDto;
import com.example.backend.models.vo.ClientVo;
import com.example.backend.services.ClientService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/v1/client")
@Tag(name = "Client", description = "Client API")
@Validated
public class ClientController {
    private final ClientService service;

    public ClientController(ClientService service) {
        this.service = service;
    }

    @GetMapping
    public Page<ClientVo> findAllClients(@ParameterObject Pageable pageable) {
        return service.findAll(pageable);
    }

    @GetMapping("/{id}")
    public ClientVo findClientById(@PathVariable @Min(1) Long id) {
        return service.findByID(id);
    }

    @PostMapping()
    public ClientVo insertClient(@Valid @RequestBody ClientDto user) {
        return service.save(user);
    }

    @PutMapping("/{id}")
    public ClientVo updateClient(@PathVariable @Min(1) Long id, @Valid @RequestBody ClientDto user) {
        return service.update(id, user);
    }
}
