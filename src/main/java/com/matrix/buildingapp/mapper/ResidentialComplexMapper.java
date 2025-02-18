package com.matrix.buildingapp.mapper;

import com.matrix.buildingapp.model.dto.requestDto.ResidentialComplexRequestDto;
import com.matrix.buildingapp.model.dto.responseDto.ResidentialComplexResponseDto;
import com.matrix.buildingapp.model.entity.ResidentialComplex;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ResidentialComplexMapper {
    ResidentialComplex toRequestDtoMapEntity(ResidentialComplexRequestDto residentialComplexRequestDto);
    ResidentialComplexResponseDto toEntityMapResponseDto(ResidentialComplex residentialComplex);
    List<ResidentialComplexResponseDto> map(List<ResidentialComplex> residentialComplex);
}
