package com.example.backend.models.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

public record TransactionDto(@NotNull @Min(1) Long clientId, @NotNull @Min(1) Long stockId,
                             @NotNull Integer shares) implements Serializable {
}
