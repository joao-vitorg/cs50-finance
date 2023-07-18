package com.example.backend.repositories;

import com.example.backend.models.Client;
import com.example.backend.models.ClientStock;
import com.example.backend.models.Stock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

import java.util.Optional;
import java.util.Set;

public interface ClientStockRepository extends JpaRepository<ClientStock, Long> {
    Optional<ClientStock> findByClientAndStock(Client Client, Stock Stock);

    @EntityGraph(attributePaths = {"stock"})
    Page<ClientStock> findByClientId(Long ClientId, Pageable pageable);

    @EntityGraph(attributePaths = {"stock"})
    Set<ClientStock> findByClientId(Long ClientId);

    @NonNull
    @EntityGraph(attributePaths = {"stock"})
    Page<ClientStock> findAll(@NonNull Pageable pageable);
}
