package com.example.backend.repositories;

import com.example.backend.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Client, Integer> {
}
