package com.example.backend.models.vo;

import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;

public record StockVo(@NotNull Long id, @NotNull String symbol, @NotNull String name, @NotNull BigDecimal price,
                      @NotNull Instant modifiedAt) implements Serializable {
}
