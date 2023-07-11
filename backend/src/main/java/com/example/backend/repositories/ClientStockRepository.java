package com.example.backend.repositories;

import com.example.backend.models.Client;
import com.example.backend.models.ClientStock;
import com.example.backend.models.Stock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ClientStockRepository extends JpaRepository<ClientStock, Long> {
    @Query("select c from ClientStock c where c.client = ?1 and c.stock = ?2")
    Optional<ClientStock> findByClientAndStock(Client Client, Stock Stock);

    @Query("select c from ClientStock c where c.client.id = ?1")
    Page<ClientStock> findByClientId(Long ClientId, Pageable pageable);
}
