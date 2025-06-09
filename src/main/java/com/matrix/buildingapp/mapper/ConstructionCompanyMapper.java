package com.matrix.buildingapp.mapper;


import com.matrix.buildingapp.model.dto.requestDto.ConstructionCompanyRequestDto;
import com.matrix.buildingapp.model.dto.responseDto.ConstructionCompanyResponseDto;
import com.matrix.buildingapp.model.entity.ConstructionCompany;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;
@Mapper(componentModel = "spring")
public interface ConstructionCompanyMapper {
    ConstructionCompany mapToEntity(ConstructionCompanyRequestDto constructionCompanyRequestDto);
    ConstructionCompanyResponseDto mapToResponse(ConstructionCompany constructionCompany);
    ConstructionCompany map(ConstructionCompanyRequestDto companyRequestDto, @MappingTarget ConstructionCompany constructionCompany);
}
