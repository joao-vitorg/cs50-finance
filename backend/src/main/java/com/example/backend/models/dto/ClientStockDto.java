package com.example.backend.models.dto;

import java.io.Serializable;

/**
 * DTO for {@link com.example.backend.models.ClientStock}
 */
public record ClientStockDto(Integer clientId, Integer stockId, Integer shares) implements Serializable {
}
