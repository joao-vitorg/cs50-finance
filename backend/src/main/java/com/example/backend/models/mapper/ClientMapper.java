package com.example.backend.models.mapper;

import com.example.backend.models.Client;
import com.example.backend.models.dto.ClientDto;
import com.example.backend.models.dto.ClientVo;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface ClientMapper {
    Client toEntity(ClientVo clientVo);

    ClientVo toDto(Client client);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Client partialUpdate(ClientVo clientVo, @MappingTarget Client client);

    Client toEntity(ClientDto clientDto);

    ClientDto toDto1(Client client);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Client partialUpdate(ClientDto clientDto, @MappingTarget Client client);
}
