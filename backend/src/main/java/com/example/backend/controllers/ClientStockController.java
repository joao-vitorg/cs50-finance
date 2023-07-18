package com.example.backend.controllers;

import com.example.backend.models.vo.ClientStockVo;
import com.example.backend.services.ClientStockService;
import io.swagger.v3.oas.annotations.Parameter;
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

import java.math.BigDecimal;

@RestController
@RequestMapping(value = "api/v1/client")
@Tag(name = "Client Stock", description = "Client Stock API")
@Validated
public class ClientStockController {
    private final ClientStockService service;

    public ClientStockController(ClientStockService service) {
        this.service = service;
    }

    @GetMapping("/stock")
    public Page<ClientStockVo> findAllClientStocks(@ParameterObject Pageable pageable) {
        return service.findAll(pageable);
    }

    @GetMapping("/{id}/virtualBalance")
    public BigDecimal getVirtualBalance(@PathVariable @Min(1) Long id) {
        return service.getVirtualBalance(id);
    }

    @GetMapping("/{id}/stock")
    public Page<ClientStockVo> findClientStockByClientId(@PathVariable @Min(1) Long id, @Parameter(hidden = true) Pageable pageable) {
        return service.findByClientId(id, pageable);
    }
}
