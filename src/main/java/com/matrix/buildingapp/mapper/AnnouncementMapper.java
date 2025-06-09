package com.matrix.buildingapp.mapper;


import com.matrix.buildingapp.model.dto.requestDto.AnnouncementRequestDto;
import com.matrix.buildingapp.model.dto.responseDto.AnnouncementResponseDto;
import com.matrix.buildingapp.model.entity.Announcement;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AnnouncementMapper {
    Announcement mapToEntity(AnnouncementRequestDto announcementRequestDto);
    AnnouncementResponseDto mapToResponse(Announcement announcement);
    Announcement map(AnnouncementRequestDto announcementRequestDto, @MappingTarget Announcement announcement);
}
