package com.matrix.buildingapp.service;


import com.matrix.buildingapp.model.dto.requestDto.ResidentialComplexRequestDto;
import com.matrix.buildingapp.model.dto.responseDto.AnnouncementResponseDto;
import com.matrix.buildingapp.model.dto.responseDto.ResidentialComplexResponseDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ResidentialComplexService {
    ResidentialComplexResponseDto add(ResidentialComplexRequestDto residentialComplexRequestDto);
    ResidentialComplexResponseDto findById(Integer id);
    ResidentialComplexResponseDto update(ResidentialComplexRequestDto residentialComplexRequestDto);
    void delete(Integer id);
    List<ResidentialComplexResponseDto> findAll();
    List<AnnouncementResponseDto> getAnnouncementByResidentialComplex(Integer id);

}
