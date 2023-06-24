package com.example.backend.models.dto;

import com.example.backend.models.Stock;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * DTO for {@link com.example.backend.models.ClientStock}
 */
public record ClientStockVo(Integer id, ClientVo client, Stock stock, Integer shares,
                            BigDecimal total) implements Serializable {
}
