package com.example.backend.repositories;

import com.example.backend.models.UserStock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserStockRepository extends JpaRepository<UserStock, Integer> {
}
