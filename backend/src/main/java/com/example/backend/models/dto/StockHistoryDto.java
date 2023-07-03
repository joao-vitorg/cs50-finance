package com.example.backend.models.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;

public record StockHistoryDto(Integer id, Integer stockId, BigDecimal price,
                              Instant createdAt) implements Serializable {
}
