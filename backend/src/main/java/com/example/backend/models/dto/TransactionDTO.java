package com.example.backend.models.dto;

import java.io.Serializable;

public record TransactionDTO(Integer clientId, Integer stockId, Integer shares) implements Serializable {
}
