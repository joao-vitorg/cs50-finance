package com.example.backend.repositories;

import com.example.backend.models.StockHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StockHistoryRepository extends JpaRepository<StockHistory, Integer> {
    @Query("select s from StockHistory s where s.stock.id = ?1")
    List<StockHistory> findByStockId(Integer id);
}
