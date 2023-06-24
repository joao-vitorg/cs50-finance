package com.example.backend.models.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;

/**
 * DTO for {@link com.example.backend.models.Stock}
 */
public record StockVo(Integer id, String symbol, String name, BigDecimal price,
                      Instant modifiedAt) implements Serializable {
}
