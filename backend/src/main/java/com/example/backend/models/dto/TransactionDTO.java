package com.example.backend.models.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public record TransactionDTO(Integer clientId, Integer stockId, Integer shares,
                             BigDecimal price) implements Serializable {
}
