package com.example.backend.models.mapper;

import com.example.backend.models.StockHistory;
import com.example.backend.models.dto.StockHistoryVo;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface StockHistoryMapper {
    StockHistoryVo toDto(StockHistory stockHistory);
}
