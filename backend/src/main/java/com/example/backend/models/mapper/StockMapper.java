package com.example.backend.models.mapper;

import com.example.backend.models.Stock;
import com.example.backend.models.dto.StockVo;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface StockMapper {
    Stock toEntity(StockVo stockVo);

    StockVo toDto(Stock stock);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Stock partialUpdate(StockVo stockVo, @MappingTarget Stock stock);
}
