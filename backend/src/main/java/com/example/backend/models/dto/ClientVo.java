package com.example.backend.models.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public record ClientVo(Long id, String username, BigDecimal balance,
                       BigDecimal virtualBalance) implements Serializable {
}
