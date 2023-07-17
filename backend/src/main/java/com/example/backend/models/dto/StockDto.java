package com.example.backend.models.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.io.Serializable;
import java.math.BigDecimal;

public record StockDto(@NotBlank String symbol, @NotBlank String name,
                       @NotNull @Positive BigDecimal price) implements Serializable {
}
