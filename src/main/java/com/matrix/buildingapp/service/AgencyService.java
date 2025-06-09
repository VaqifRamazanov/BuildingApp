package com.matrix.buildingapp.service;



import com.matrix.buildingapp.model.dto.requestDto.AgencyRequestDto;
import com.matrix.buildingapp.model.dto.responseDto.AgencyResponseDto;
import com.matrix.buildingapp.model.dto.responseDto.AnnouncementResponseDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface AgencyService {
    AgencyResponseDto add(AgencyRequestDto agencyRequestDto);
    AgencyResponseDto getById(Integer id);
    AgencyResponseDto update(Integer id,AgencyRequestDto agencyRequestDto);
    void delete(Integer id);
    List<AgencyResponseDto> getAll();
     List<AnnouncementResponseDto> getAnnouncementByAgency(Integer id);
}
