package com.matrix.buildingapp.service;

import com.matrix.buildingapp.model.dto.requestDto.CardRequestDto;
import com.matrix.buildingapp.model.dto.responseDto.CardResponseDto;

public interface CardService {

    CardResponseDto add(Long id, CardRequestDto requestDto);

    CardResponseDto getById(Long id);


    void delete(Long id);
}
