package com.example.backend.services;

import com.example.backend.models.Client;
import com.example.backend.models.dto.ClientDto;
import com.example.backend.models.dto.ClientVo;
import com.example.backend.models.mapper.EntityMapper;
import com.example.backend.repositories.ClientRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClientService {
    private final ClientRepository repository;
    private final EntityMapper mapper;

    public ClientService(ClientRepository repository, EntityMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Transactional(readOnly = true)
    public ClientVo findByID(Long id) {
        return mapper.map(repository.findById(id).orElseThrow());
    }

    @Transactional(readOnly = true)
    public Page<ClientVo> findAll(Pageable pageable) {
        return repository.findAll(pageable).map(mapper::map);
    }

    @Transactional()
    public ClientVo save(ClientDto entityDTO) {
        Client entity = mapper.map(entityDTO);
        return mapper.map(repository.save(entity));
    }
}
