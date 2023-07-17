package com.example.backend.models.vo;

import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;

public record TransactionVo(@NotNull Long id, @NotNull Long clientId, @NotNull Long stockId, @NotNull Integer shares,
                            @NotNull BigDecimal total, @NotNull Instant createdAt) implements Serializable {
}
