package com.example.backend.models.dto;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * DTO for {@link com.example.backend.models.Client}
 */
public record ClientVo(Integer id, String username, BigDecimal balance,
                       BigDecimal VirtualBalance) implements Serializable {
}
