package com.example.backend.repositories;

import com.example.backend.models.ClientStock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientStockRepository extends JpaRepository<ClientStock, Integer> {
}
