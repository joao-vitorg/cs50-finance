package com.example.backend.controllers;

import com.example.backend.exceptions.ExceptionResponse;
import com.example.backend.models.dto.StockDto;
import com.example.backend.models.dto.StockHistoryDto;
import com.example.backend.services.StockHistoryService;
import com.example.backend.services.StockService;
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
@RequestMapping(value = "api/v1/stock", produces = {"application/json", "application/xml", "application/x-yml"})
@Tag(name = "Stock", description = "Stock API")
public class StockController {
    private final StockService service;
    private final StockHistoryService stockHistoryService;

    public StockController(StockService service, StockHistoryService stockHistoryService) {
        this.service = service;
        this.stockHistoryService = stockHistoryService;
    }

    @GetMapping
    @Operation(responses = {
            @ApiResponse(description = "Success", responseCode = "200", content = @Content(array = @ArraySchema(schema = @Schema(implementation = StockDto.class)))),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content(schema = @Schema(implementation = ExceptionResponse.class))),
            @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content(schema = @Schema(implementation = ExceptionResponse.class)))
    })
    public List<StockDto> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    @Operation(responses = {
            @ApiResponse(description = "Success", responseCode = "200", content = @Content(schema = @Schema(implementation = StockDto.class))),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content(schema = @Schema(implementation = ExceptionResponse.class))),
            @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content(schema = @Schema(implementation = ExceptionResponse.class)))
    })
    public StockDto findById(@PathVariable Integer id) {
        return service.findByID(id);
    }

    @GetMapping("/{id}/history")
    @Operation(responses = {
            @ApiResponse(description = "Success", responseCode = "200", content = @Content(array = @ArraySchema(schema = @Schema(implementation = StockDto.class)))),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content(schema = @Schema(implementation = ExceptionResponse.class))),
            @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content(schema = @Schema(implementation = ExceptionResponse.class)))
    })
    public List<StockHistoryDto> findByStockId(@PathVariable Integer id) {
        return stockHistoryService.findByStockId(id);
    }

    @GetMapping("/history")
    @Operation(responses = {
            @ApiResponse(description = "Success", responseCode = "200", content = @Content(array = @ArraySchema(schema = @Schema(implementation = StockDto.class)))),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content(schema = @Schema(implementation = ExceptionResponse.class))),
            @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content(schema = @Schema(implementation = ExceptionResponse.class)))
    })
    public List<StockHistoryDto> findAllStockHistory() {
        return stockHistoryService.findAll();
    }
}
