package com.example.backend.models.mapper;

import com.example.backend.models.Transaction;
import com.example.backend.models.dto.TransactionDTO;
import com.example.backend.models.dto.TransactionVo;
import com.example.backend.services.ClientService;
import com.example.backend.services.StockService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, uses = {ClientService.class, StockService.class})
public interface TransactionMapper {
    @Mapping(source = "stockId", target = "stock")
    @Mapping(source = "clientId", target = "client")
    Transaction toEntity(TransactionDTO transactionDTO);

    TransactionVo toDto(Transaction transaction);
}
