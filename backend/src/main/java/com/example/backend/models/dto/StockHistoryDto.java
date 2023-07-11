package com.example.backend.models.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;

public record StockHistoryDto(Long id, Long stockId, BigDecimal price, Instant createdAt) implements Serializable {
}
