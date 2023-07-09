package com.example.backend.services;

import com.example.backend.models.Client;
import com.example.backend.models.dto.ClientDto;
import com.example.backend.models.dto.ClientVo;
import com.example.backend.models.mapper.EntityMapper;
import com.example.backend.repositories.ClientRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientService {
    private final ClientRepository repository;
    private final EntityMapper mapper;

    public ClientService(ClientRepository repository, EntityMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Transactional(readOnly = true)
    public ClientVo findByID(Integer id) {
        return mapper.map(repository.findById(id).orElseThrow());
    }

    @Transactional(readOnly = true)
    public List<ClientVo> findAll() {
        return repository.findAll().stream().map(mapper::map).collect(Collectors.toList());
    }

    @Transactional()
    public ClientVo save(ClientDto entityDTO) {
        Client entity = mapper.map(entityDTO);
        return mapper.map(repository.save(entity));
    }
}
