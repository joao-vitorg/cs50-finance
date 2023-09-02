package com.example.backend.models.vo;

import jakarta.validation.constraints.NotNull;

public interface StockHistoryVo {
    @NotNull Double getOpen();

    @NotNull Double getHigh();

    @NotNull Double getClose();

    @NotNull Double getLow();

    @NotNull String getTime();
}
