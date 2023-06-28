package com.example.backend.services;

import com.example.backend.models.Client;
import com.example.backend.models.dto.ClientDto;
import com.example.backend.models.dto.ClientVo;
import com.example.backend.models.mapper.ClientMapper;
import com.example.backend.repositories.ClientRepository;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Service
public class ClientService {
    private final ClientRepository repository;
    private final ClientMapper clientMapper;

    public ClientService(ClientRepository repository,
                         ClientMapper clientMapper) {
        this.repository = repository;
        this.clientMapper = clientMapper;
    }

    public void deposit(Client client, BigDecimal amount) {
        client.setBalance(client.getBalance().add(amount));
    }

    public void withdraw(Client client, BigDecimal amount) {
        if (client.getBalance().compareTo(amount) < 0) throw new Error();
        client.setBalance(client.getBalance().subtract(amount));
    }

    public ClientVo findByID(Integer id) {
        return clientMapper.toDto(repository.findById(id).orElseThrow());
    }

    public List<ClientVo> findAll() {
        return repository.findAll().stream().map(clientMapper::toDto).collect(Collectors.toList());
    }

    public ClientVo save(ClientDto entityDTO) {
        Client entity = clientMapper.toEntity(entityDTO);
        return clientMapper.toDto(repository.save(entity));
    }

    public void deleteByID(Integer id) {
        repository.deleteById(id);
    }
}
