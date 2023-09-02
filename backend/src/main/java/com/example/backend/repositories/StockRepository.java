package com.example.backend.repositories;

import com.example.backend.models.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository extends JpaRepository<Stock, Long> {
    boolean existsBySymbol(String symbol);
}
