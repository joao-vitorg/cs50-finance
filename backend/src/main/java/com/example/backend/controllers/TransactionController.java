package com.example.backend.controllers;

import com.example.backend.exceptions.ExceptionResponse;
import com.example.backend.models.dto.TransactionDto;
import com.example.backend.services.TransactionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/transaction", produces = {"application/json", "application/xml", "application/x-yml"})
@Tag(name = "Transaction", description = "Transaction API")
public class TransactionController {
    private final TransactionService service;

    public TransactionController(TransactionService service) {
        this.service = service;
    }

    @GetMapping
    @Operation(responses = {
            @ApiResponse(description = "Success", responseCode = "200", content = @Content(array = @ArraySchema(schema = @Schema(implementation = TransactionDto.class)))),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content(schema = @Schema(implementation = ExceptionResponse.class))),
            @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content(schema = @Schema(implementation = ExceptionResponse.class)))
    })
    public List<TransactionDto> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    @Operation(responses = {
            @ApiResponse(description = "Success", responseCode = "200", content = @Content(schema = @Schema(implementation = TransactionDto.class))),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content(schema = @Schema(implementation = ExceptionResponse.class))),
            @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content(schema = @Schema(implementation = ExceptionResponse.class)))
    })
    public TransactionDto findById(@PathVariable Integer id) {
        return service.findByID(id);
    }

    @GetMapping("/client/{id}")
    @Operation(responses = {
            @ApiResponse(description = "Success", responseCode = "200", content = @Content(array = @ArraySchema(schema = @Schema(implementation = TransactionDto.class)))),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content(schema = @Schema(implementation = ExceptionResponse.class))),
            @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content(schema = @Schema(implementation = ExceptionResponse.class)))
    })
    public List<TransactionDto> findByClientId(@PathVariable Integer id) {
        return service.findByClientId(id);
    }

    @PostMapping(consumes = {"application/json", "application/xml", "application/x-yml"})
    @Operation(responses = {
            @ApiResponse(description = "Success", responseCode = "200", content = @Content(schema = @Schema(implementation = TransactionDto.class))),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content(schema = @Schema(implementation = ExceptionResponse.class))),
            @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content(schema = @Schema(implementation = ExceptionResponse.class)))
    })
    public TransactionDto insert(@RequestBody TransactionDto transaction) {
        return service.save(transaction);
    }
}
