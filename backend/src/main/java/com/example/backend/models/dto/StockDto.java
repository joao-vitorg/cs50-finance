package com.example.backend.models.dto;

import jakarta.validation.constraints.*;

import java.io.Serializable;
import java.math.BigDecimal;

public record StockDto(@NotBlank @Size(min = 3, max = 10) @Pattern(regexp = "^[A-Z0-9]+$") String symbol,
                       @NotBlank @Size(min = 3, max = 100) String name,
                       @NotNull @Positive BigDecimal price) implements Serializable {
}
