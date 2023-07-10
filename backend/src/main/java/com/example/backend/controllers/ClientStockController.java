package com.example.backend.controllers;

import com.example.backend.exceptions.ExceptionResponse;
import com.example.backend.models.dto.ClientStockDto;
import com.example.backend.services.ClientStockService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/client", produces = {"application/json", "application/xml", "application/x-yml"})
@Tag(name = "Client Stock", description = "Client Stock API")
public class ClientStockController {
    private final ClientStockService service;

    public ClientStockController(ClientStockService service) {
        this.service = service;
    }

    @GetMapping("/stock")
    @Operation(responses = {
            @ApiResponse(description = "Success", responseCode = "200", content = @Content(array = @ArraySchema(schema = @Schema(implementation = ClientStockDto.class)))),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content(schema = @Schema(implementation = ExceptionResponse.class))),
            @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content(schema = @Schema(implementation = ExceptionResponse.class)))
    })
    public List<ClientStockDto> findStock() {
        return service.findAll();
    }

    @GetMapping("/{id}/stock")
    @Operation(responses = {
            @ApiResponse(description = "Success", responseCode = "200", content = @Content(array = @ArraySchema(schema = @Schema(implementation = ClientStockDto.class)))),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content(schema = @Schema(implementation = ExceptionResponse.class))),
            @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content(schema = @Schema(implementation = ExceptionResponse.class)))
    })
    public List<ClientStockDto> findStockById(@PathVariable Integer id) {
        return service.findByClientID(id);
    }
}
