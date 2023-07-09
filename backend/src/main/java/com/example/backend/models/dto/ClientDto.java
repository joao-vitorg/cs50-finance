package com.example.backend.models.dto;

import java.io.Serializable;

public record ClientDto(Integer id, String username, String password) implements Serializable {
}
