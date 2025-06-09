package com.matrix.buildingapp.mapper;


import com.matrix.buildingapp.model.dto.UserDto;
import com.matrix.buildingapp.model.dto.requestDto.UserRequestDto;
import com.matrix.buildingapp.model.dto.responseDto.UserResponseDto;
import com.matrix.buildingapp.model.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",uses = {AnnouncementMapper.class,AgencyMapper.class, CardMapper.class}
        ,nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)

public interface UserMapper {
    User mapToEntity(UserRequestDto userRequestDto);

    UserResponseDto mapToResponse(User user);

    void toUpdate(UserDto userDto, @MappingTarget User user);
}