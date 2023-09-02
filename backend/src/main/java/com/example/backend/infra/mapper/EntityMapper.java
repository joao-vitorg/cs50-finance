package com.example.backend.infra.mapper;

import com.example.backend.models.Client;
import com.example.backend.models.ClientStock;
import com.example.backend.models.Stock;
import com.example.backend.models.Transaction;
import com.example.backend.models.dto.ClientDto;
import com.example.backend.models.dto.StockDto;
import com.example.backend.models.dto.TransactionDto;
import com.example.backend.models.vo.ClientStockVo;
import com.example.backend.models.vo.ClientVo;
import com.example.backend.models.vo.StockVo;
import com.example.backend.models.vo.TransactionVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring", uses = {ReferenceMapper.class})
public interface EntityMapper {
    Client toClient(Long id);

    Stock toStock(Long id);

    // Dto

    @Mapping(source = "stockId", target = "stock")
    @Mapping(source = "clientId", target = "client")
    Transaction map(TransactionDto transactionDto);

    Client map(ClientDto clientDto);

    Stock map(StockDto stockDto);

    // Vo

    ClientVo map(Client client);

    StockVo map(Stock stock);

    @Mapping(source = "client.id", target = "clientId")
    ClientStockVo map(ClientStock clientStock);

    @Mapping(source = "client.id", target = "clientId")
    TransactionVo map(Transaction transaction);
}
