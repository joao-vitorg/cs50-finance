package com.example.backend.repositories;

import com.example.backend.models.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    @Query("select t from Transaction t where t.client.id = ?1")
    Page<Transaction> findByClientId(Long id, Pageable pageable);
}
