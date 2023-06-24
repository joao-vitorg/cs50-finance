package com.example.backend.services;

import com.example.backend.models.StockHistory;
import com.example.backend.models.dto.StockHistoryDTO;
import com.example.backend.repositories.StockHistoryRepository;
import com.example.backend.services.mapper.DozerMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockHistoryService {
    private final StockHistoryRepository repository;

    public StockHistoryService(StockHistoryRepository repository) {
        this.repository = repository;
    }

    public StockHistoryDTO findByID(Integer id) {
        return DozerMapper.parseObject(repository.findById(id).orElseThrow(), StockHistoryDTO.class);
    }

    public List<StockHistoryDTO> findAll() {
        return DozerMapper.parseListObject(repository.findAll(), StockHistoryDTO.class);
    }

    public StockHistoryDTO save(StockHistoryDTO entityDTO) {
        StockHistory entity = DozerMapper.parseObject(entityDTO, StockHistory.class);
        return DozerMapper.parseObject(repository.save(entity), StockHistoryDTO.class);
    }

    public void deleteByID(Integer id) {
        repository.deleteById(id);
    }
}
