package com.matrix.buildingapp.service;


import com.matrix.buildingapp.model.dto.requestDto.AnnouncementRequestDto;
import com.matrix.buildingapp.model.dto.responseDto.AnnouncementResponseDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface AnnouncementService {
    AnnouncementResponseDto add(AnnouncementRequestDto announcementRequestDto);
    AnnouncementResponseDto getById(Integer id);
    AnnouncementResponseDto update(Integer id,AnnouncementRequestDto announcementRequestDto);
    void delete(Integer id);
    List<AnnouncementResponseDto> getAll();
}
