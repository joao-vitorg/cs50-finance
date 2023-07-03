package com.example.backend.repositories;

import com.example.backend.models.ClientStock;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ClientStockRepository extends JpaRepository<ClientStock, Integer> {
    Optional<ClientStock> findByClientIdAndStockId(Integer ClientId, Integer StockId);

    List<ClientStock> findByClientId(Integer ClientId);
}
