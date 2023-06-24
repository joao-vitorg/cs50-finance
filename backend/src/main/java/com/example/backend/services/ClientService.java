package com.example.backend.services;

import com.example.backend.models.Client;
import com.example.backend.models.dto.ClientDTO;
import com.example.backend.repositories.ClientRepository;
import com.example.backend.services.mapper.DozerMapper;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.util.List;

@Getter
@Service
public class ClientService {
    private final ClientRepository repository;

    public ClientService(ClientRepository repository) {
        this.repository = repository;
    }

    public ClientDTO findByID(Integer id) {
        return DozerMapper.parseObject(repository.findById(id).orElseThrow(), ClientDTO.class);
    }

    public List<ClientDTO> findAll() {
        return DozerMapper.parseListObject(repository.findAll(), ClientDTO.class);
    }

    public ClientDTO save(ClientDTO entityDTO) {
        Client entity = DozerMapper.parseObject(entityDTO, Client.class);
        return DozerMapper.parseObject(repository.save(entity), ClientDTO.class);
    }

    public void deleteByID(Integer id) {
        repository.deleteById(id);
    }
}
