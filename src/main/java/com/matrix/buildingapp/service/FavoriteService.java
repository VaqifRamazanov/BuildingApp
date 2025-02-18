package com.matrix.buildingapp.service;

import com.matrix.buildingapp.model.dto.requestDto.FavoriteRequestDto;
import com.matrix.buildingapp.model.dto.responseDto.AnnouncementResponseDto;

import java.util.List;

public interface FavoriteService {
    void add(FavoriteRequestDto requestDto);
    void delete(FavoriteRequestDto requestDto);
    List<AnnouncementResponseDto> getAll(Long id);
}
