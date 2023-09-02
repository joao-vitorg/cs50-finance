package com.example.backend.infra.exceptions.handler;

import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.time.Instant;

public record ExceptionResponse(@NotNull Instant timestamp, @NotNull String message,
                                @NotNull String datails) implements Serializable {
}
