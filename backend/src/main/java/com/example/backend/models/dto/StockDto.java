package com.example.backend.models.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;

public record StockDto(Long id, String symbol, String name, BigDecimal price,
                       Instant modifiedAt) implements Serializable {
}
