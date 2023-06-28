package com.example.backend.models.dto;

import com.example.backend.models.Stock;

import java.io.Serializable;
import java.math.BigDecimal;

public record ClientStockVo(Integer id, ClientVo client, Stock stock, Integer shares,
                            BigDecimal total) implements Serializable {
}
