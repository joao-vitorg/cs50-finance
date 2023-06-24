package com.example.backend.models.mapper;

import com.example.backend.models.StockHistory;
import com.example.backend.models.dto.StockHistoryVo;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface StockHistoryMapper {
    StockHistory toEntity(StockHistoryVo stockHistoryVo);

    StockHistoryVo toDto(StockHistory stockHistory);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    StockHistory partialUpdate(StockHistoryVo stockHistoryVo, @MappingTarget StockHistory stockHistory);
}
