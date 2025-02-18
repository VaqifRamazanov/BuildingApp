package com.matrix.buildingapp.mapper;
import com.matrix.buildingapp.model.dto.requestDto.AgencyRequestDto;
import com.matrix.buildingapp.model.dto.responseDto.AgencyResponseDto;
import com.matrix.buildingapp.model.entity.Agency;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AgencyMapper {
    Agency toRequestDtoMapEntity(AgencyRequestDto agencyRequestDto);

    AgencyResponseDto toEntityMapResponseDto(Agency agency);

    List<AgencyResponseDto> map(List<Agency> agency);
}
