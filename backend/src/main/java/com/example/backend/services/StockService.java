package com.example.backend.services;

import com.example.backend.models.Stock;
import com.example.backend.models.dto.StockDTO;
import com.example.backend.repositories.StockRepository;
import com.example.backend.services.mapper.DozerMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockService {
    private final StockRepository repository;

    public StockService(StockRepository repository) {
        this.repository = repository;
    }

    public StockDTO findByID(Integer id) {
        return DozerMapper.parseObject(repository.findById(id).orElseThrow(), StockDTO.class);
    }

    public List<StockDTO> findAll() {
        return DozerMapper.parseListObject(repository.findAll(), StockDTO.class);
    }

    public StockDTO save(StockDTO entityDTO) {
        Stock entity = DozerMapper.parseObject(entityDTO, Stock.class);
        return DozerMapper.parseObject(repository.save(entity), StockDTO.class);
    }

    public void deleteByID(Integer id) {
        repository.deleteById(id);
    }
}
