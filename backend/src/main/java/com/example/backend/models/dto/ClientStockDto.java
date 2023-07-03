package com.example.backend.models.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public record ClientStockDto(Integer id, Integer clientId, Integer stockId, Integer shares,
                             BigDecimal total) implements Serializable {
}
