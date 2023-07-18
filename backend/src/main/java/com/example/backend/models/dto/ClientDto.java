package com.example.backend.models.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.io.Serializable;


public record ClientDto(@NotBlank @Size(min = 3, max = 20) @Pattern(regexp = "^[a-zA-Z0-9_-]+$") String username,
                        @NotBlank @Size(min = 3) String password) implements Serializable {
}
