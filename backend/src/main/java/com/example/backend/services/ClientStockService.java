package com.example.backend.services;

import com.example.backend.models.ClientStock;
import com.example.backend.models.dto.ClientStockDTO;
import com.example.backend.repositories.ClientStockRepository;
import com.example.backend.services.mapper.DozerMapper;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.util.List;

@Getter
@Service
public class ClientStockService {
    private final ClientStockRepository repository;

    public ClientStockService(ClientStockRepository repository) {
        this.repository = repository;
    }

    public ClientStockDTO findByID(Integer id) {
        return DozerMapper.parseObject(repository.findById(id).orElseThrow(), ClientStockDTO.class);
    }

    public List<ClientStockDTO> findAll() {
        return DozerMapper.parseListObject(repository.findAll(), ClientStockDTO.class);
    }

    public ClientStockDTO save(ClientStockDTO entityDTO) {
        ClientStock entity = DozerMapper.parseObject(entityDTO, ClientStock.class);
        return DozerMapper.parseObject(repository.save(entity), ClientStockDTO.class);
    }

    public void deleteByID(Integer id) {
        repository.deleteById(id);
    }
}
