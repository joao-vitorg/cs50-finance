package com.example.backend.models.mapper;

import com.example.backend.models.*;
import com.example.backend.models.dto.ClientDto;
import com.example.backend.models.dto.StockDto;
import com.example.backend.models.dto.TransactionDto;
import com.example.backend.models.vo.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring", uses = {ReferenceMapper.class})
public interface EntityMapper {
    Client toClient(Long id);

    Stock toStock(Long id);

    @Mapping(source = "stockId", target = "stock")
    @Mapping(source = "clientId", target = "client")
    Transaction map(TransactionDto transactionDto);

    Client map(ClientDto clientDto);

    Stock map(StockDto stockDto);

    ClientVo map(Client client);

    StockVo map(Stock stock);

    @Mapping(source = "stock.id", target = "stockId")
    StockHistoryVo map(StockHistory stockHistory);

    @Mapping(source = "stock.id", target = "stockId")
    @Mapping(source = "client.id", target = "clientId")
    ClientStockVo map(ClientStock clientStock);

    @Mapping(source = "stock.id", target = "stockId")
    @Mapping(source = "client.id", target = "clientId")
    TransactionVo map(Transaction transaction);
}
