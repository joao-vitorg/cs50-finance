package com.example.backend.models.mapper;

import com.example.backend.models.*;
import com.example.backend.models.dto.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring", uses = {ReferenceMapper.class})
public interface EntityMapper {
    Client toClient(Integer id);

    Stock toStock(Integer id);

    Client map(ClientDto clientDto);

    ClientVo map(Client client);

    StockDto map(Stock stock);

    @Mapping(source = "stock.id", target = "stockId")
    StockHistoryDto map(StockHistory stockHistory);

    @Mapping(source = "stockId", target = "stock")
    @Mapping(source = "clientId", target = "client")
    ClientStock map(ClientStockDto clientStockDto);

    @Mapping(source = "stockId", target = "stock")
    @Mapping(source = "clientId", target = "client")
    ClientStock toClientStock(Integer clientId, Integer stockId);

    @Mapping(source = "stock.id", target = "stockId")
    @Mapping(source = "client.id", target = "clientId")
    ClientStockDto map(ClientStock clientStock);

    @Mapping(source = "stockId", target = "stock")
    @Mapping(source = "clientId", target = "client")
    Transaction map(TransactionDto transactionDTO);

    @Mapping(source = "stock.id", target = "stockId")
    @Mapping(source = "client.id", target = "clientId")
    TransactionDto map(Transaction transaction);
}
