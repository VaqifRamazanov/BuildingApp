package com.matrix.buildingapp.mapper;

import com.matrix.buildingapp.model.dto.requestDto.ResidentialComplexRequestDto;
import com.matrix.buildingapp.model.dto.responseDto.ResidentialComplexResponseDto;
import com.matrix.buildingapp.model.entity.ResidentialComplex;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ResidentialComplexMapper {
    ResidentialComplex mapToEntity(ResidentialComplexRequestDto residentialComplexRequestDto);
    ResidentialComplexResponseDto mapToResponse(ResidentialComplex residentialComplex);
    ResidentialComplex map(ResidentialComplexRequestDto residentialComplexRequestDto, @MappingTarget ResidentialComplex residentialComplex);
}
