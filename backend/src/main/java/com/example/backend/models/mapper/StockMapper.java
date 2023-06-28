package com.example.backend.models.mapper;

import com.example.backend.models.Stock;
import com.example.backend.models.dto.StockVo;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface StockMapper {
    StockVo toDto(Stock stock);
}
