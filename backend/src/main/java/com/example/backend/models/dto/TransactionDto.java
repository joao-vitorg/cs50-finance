package com.example.backend.models.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;

public record TransactionDto(Integer id, Integer clientId, Integer stockId, Integer shares, BigDecimal total,
                             Instant createdAt) implements Serializable {
}
