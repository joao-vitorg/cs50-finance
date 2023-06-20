package com.example.backend.services;

import com.example.backend.models.UserStock;
import com.example.backend.repositories.UserStockRepository;
import lombok.Getter;
import org.springframework.stereotype.Service;

@Getter
@Service
public class UserStockService implements BaseService<UserStock> {
    private final UserStockRepository repository;

    public UserStockService(UserStockRepository repository) {
        this.repository = repository;
    }
}
