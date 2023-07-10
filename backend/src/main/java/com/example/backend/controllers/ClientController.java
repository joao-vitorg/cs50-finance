package com.example.backend.controllers;

import com.example.backend.exceptions.ExceptionResponse;
import com.example.backend.models.dto.ClientDto;
import com.example.backend.models.dto.ClientVo;
import com.example.backend.services.ClientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/client", produces = {"application/json", "application/xml", "application/x-yml"})
@Tag(name = "Client", description = "Client API")
public class ClientController {
    private final ClientService service;

    public ClientController(ClientService service) {
        this.service = service;
    }

    @GetMapping
    @Operation(responses = {
            @ApiResponse(description = "Success", responseCode = "200", content = @Content(array = @ArraySchema(schema = @Schema(implementation = ClientVo.class)))),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content(schema = @Schema(implementation = ExceptionResponse.class))),
            @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content(schema = @Schema(implementation = ExceptionResponse.class)))
    })
    public List<ClientVo> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    @Operation(responses = {
            @ApiResponse(description = "Success", responseCode = "200", content = @Content(schema = @Schema(implementation = ClientVo.class))),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content(schema = @Schema(implementation = ExceptionResponse.class))),
            @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content(schema = @Schema(implementation = ExceptionResponse.class)))
    })
    public ClientVo findById(@PathVariable Integer id) {
        return service.findByID(id);
    }

    @PostMapping(consumes = {"application/json", "application/xml", "application/x-yml"})
    @Operation(responses = {
            @ApiResponse(description = "Success", responseCode = "200", content = @Content(schema = @Schema(implementation = ClientVo.class))),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content(schema = @Schema(implementation = ExceptionResponse.class))),
            @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content(schema = @Schema(implementation = ExceptionResponse.class)))
    })
    public ClientVo insert(@RequestBody ClientDto user) {
        return service.save(user);
    }

    @PutMapping(consumes = {"application/json", "application/xml", "application/x-yml"})
    @Operation(responses = {
            @ApiResponse(description = "Success", responseCode = "200", content = @Content(schema = @Schema(implementation = ClientVo.class))),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content(schema = @Schema(implementation = ExceptionResponse.class))),
            @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content(schema = @Schema(implementation = ExceptionResponse.class)))
    })
    public ClientVo update(@RequestBody ClientDto user) {
        return service.save(user);
    }
}
