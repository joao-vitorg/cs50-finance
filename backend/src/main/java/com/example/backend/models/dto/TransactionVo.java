package com.example.backend.models.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;

/**
 * DTO for {@link com.example.backend.models.Transaction}
 */
public record TransactionVo(Integer id, ClientVo client, StockVo stock, Integer shares, BigDecimal price,
                            Instant createdAt) implements Serializable {
}
