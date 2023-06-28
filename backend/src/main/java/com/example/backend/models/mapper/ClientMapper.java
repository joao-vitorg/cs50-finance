package com.example.backend.models.mapper;

import com.example.backend.models.Client;
import com.example.backend.models.dto.ClientDto;
import com.example.backend.models.dto.ClientVo;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface ClientMapper {
    Client toEntity(ClientDto clientDto);

    ClientVo toDto(Client client);
}
