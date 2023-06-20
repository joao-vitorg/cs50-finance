package com.example.backend.services;

import com.example.backend.models.Client;
import com.example.backend.repositories.UserRepository;
import lombok.Getter;
import org.springframework.stereotype.Service;

@Getter
@Service
public class UserService implements BaseService<Client> {
    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }
}
