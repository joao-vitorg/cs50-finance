package com.example.backend.models.dto;

import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;

public record ClientDto(@NotBlank String username, @NotBlank String password) implements Serializable {
}
