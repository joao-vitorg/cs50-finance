package com.example.backend.models.vo;

import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.math.BigDecimal;

public record ClientVo(@NotNull Long id, @NotNull String username, @NotNull BigDecimal balance,
                       @NotNull BigDecimal virtualBalance) implements Serializable {
}
