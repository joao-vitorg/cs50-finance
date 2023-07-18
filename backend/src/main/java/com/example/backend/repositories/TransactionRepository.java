package com.example.backend.repositories;

import com.example.backend.models.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    @EntityGraph(attributePaths = {"stock"})
    Page<Transaction> findByClientId(Long id, Pageable pageable);

    @NonNull
    @EntityGraph(attributePaths = {"stock"})
    Page<Transaction> findAll(@NonNull Pageable pageable);
}
