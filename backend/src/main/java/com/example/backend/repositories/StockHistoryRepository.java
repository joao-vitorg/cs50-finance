package com.example.backend.repositories;

import com.example.backend.models.StockHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface StockHistoryRepository extends JpaRepository<StockHistory, Long> {
    @Query("select s from StockHistory s where s.stock.id = ?1")
    Page<StockHistory> findByStockId(Long id, Pageable pageable);
}
