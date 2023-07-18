package com.example.backend.repositories;

import com.example.backend.models.StockHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

public interface StockHistoryRepository extends JpaRepository<StockHistory, Long> {
    @EntityGraph(attributePaths = {"stock"})
    Page<StockHistory> findByStockId(Long id, Pageable pageable);

    @NonNull
    @EntityGraph(attributePaths = {"stock"})
    Page<StockHistory> findAll(@NonNull Pageable pageable);
}
