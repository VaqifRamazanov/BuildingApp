package com.matrix.buildingapp.mapper;


import com.matrix.buildingapp.model.dto.requestDto.ConstructionCompanyRequestDto;
import com.matrix.buildingapp.model.dto.responseDto.ConstructionCompanyResponseDto;
import com.matrix.buildingapp.model.entity.ConstructionCompany;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

import java.util.List;
@Mapper(componentModel = "spring")
public interface ConstructionCompanyMapper {
    ConstructionCompany toRequestDtoMapEntity(ConstructionCompanyRequestDto constructionCompanyRequestDto);
    ConstructionCompanyResponseDto toEntityMapResponseDto(ConstructionCompany constructionCompany);
    List<ConstructionCompanyResponseDto> map(List<ConstructionCompany> constructionCompanies);
}
