package com.example.backend.models.dto;

import java.io.Serializable;

/**
 * DTO for {@link com.example.backend.models.Client}
 */
public record ClientDto(String username, String password) implements Serializable {
}
