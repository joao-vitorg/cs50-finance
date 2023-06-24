package com.example.backend.models.mapper;

import com.example.backend.models.Transaction;
import com.example.backend.models.dto.TransactionDTO;
import com.example.backend.models.dto.TransactionVo;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, uses = {ClientMapper.class, StockMapper.class})
public interface TransactionMapper {
    @Mapping(source = "stockId", target = "stock.id")
    @Mapping(source = "clientId", target = "client.id")
    Transaction toEntity(TransactionDTO transactionDTO);

    @InheritInverseConfiguration(name = "toEntity")
    TransactionDTO toDto(Transaction transaction);

    @InheritConfiguration(name = "toEntity")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Transaction partialUpdate(TransactionDTO transactionDTO, @MappingTarget Transaction transaction);

    Transaction toEntity(TransactionVo transactionVo);

    TransactionVo toDto1(Transaction transaction);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Transaction partialUpdate(TransactionVo transactionVo, @MappingTarget Transaction transaction);
}
