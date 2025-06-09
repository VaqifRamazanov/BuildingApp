package com.matrix.buildingapp.mapper;
import com.matrix.buildingapp.model.dto.requestDto.AgencyRequestDto;
import com.matrix.buildingapp.model.dto.responseDto.AgencyResponseDto;
import com.matrix.buildingapp.model.entity.Agency;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AgencyMapper {
    Agency mapToEntity(AgencyRequestDto agencyRequestDto);

    AgencyResponseDto mapToResponse(Agency agency);

    Agency map(AgencyRequestDto agencyRequestDto, @MappingTarget Agency agency);
}
