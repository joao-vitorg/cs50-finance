package com.example.backend.models.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public record ClientStockDto(Long id, Long clientId, Long stockId, Integer shares,
                             BigDecimal total) implements Serializable {
}
