package com.example.backend.models.vo;

import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;

public record StockHistoryVo(@NotNull Long id, @NotNull Long stockId, @NotNull BigDecimal price,
                             @NotNull Instant createdAt) implements Serializable {
}
