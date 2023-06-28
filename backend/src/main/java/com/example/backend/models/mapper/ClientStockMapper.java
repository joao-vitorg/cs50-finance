package com.example.backend.models.mapper;

import com.example.backend.models.ClientStock;
import com.example.backend.models.dto.ClientStockDto;
import com.example.backend.models.dto.ClientStockVo;
import com.example.backend.services.ClientService;
import com.example.backend.services.StockService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, uses = {ClientService.class, StockService.class})
public interface ClientStockMapper {
    @Mapping(source = "stockId", target = "stock")
    @Mapping(source = "clientId", target = "client")
    ClientStock toEntity(ClientStockDto clientStockDto);

    ClientStockVo toDto(ClientStock clientStock);
}
