package com.example.backend.models.dto;

import com.example.backend.models.Stock;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;

public record StockHistoryVo(Integer id, Stock stock, BigDecimal price, Instant createdAt) implements Serializable {
}
