package com.example.backend.repositories;

import com.example.backend.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
    @Query("select t from Transaction t where t.client.id = ?1")
    List<Transaction> findByClientId(Integer id);
}
