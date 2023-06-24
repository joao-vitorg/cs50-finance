package com.example.backend.models.mapper;

import com.example.backend.models.ClientStock;
import com.example.backend.models.dto.ClientStockVo;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, uses = {ClientMapper.class})
public interface ClientStockMapper {
    ClientStock toEntity(ClientStockVo clientStockVo);

    ClientStockVo toDto(ClientStock clientStock);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    ClientStock partialUpdate(ClientStockVo clientStockVo, @MappingTarget ClientStock clientStock);
}
