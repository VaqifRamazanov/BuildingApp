package com.matrix.buildingapp.service;

import com.matrix.buildingapp.model.dto.UserDto;
import com.matrix.buildingapp.model.dto.responseDto.AnnouncementResponseDto;
import com.matrix.buildingapp.model.dto.responseDto.CardResponseDto;
import com.matrix.buildingapp.model.dto.responseDto.UserResponseDto;

import java.util.List;

public interface UserService {
    UserResponseDto getById(Long id);
    UserResponseDto update(Long id, UserDto userDto);
    void delete(Long id);
    List<UserResponseDto> getAll();
    List<AnnouncementResponseDto> getAnnouncementByUser(Long id);
    CardResponseDto getCardByUser(Long id);


}
