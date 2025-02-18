package com.matrix.buildingapp.mapper;


import com.matrix.buildingapp.model.dto.requestDto.AnnouncementRequestDto;
import com.matrix.buildingapp.model.dto.responseDto.AnnouncementResponseDto;
import com.matrix.buildingapp.model.entity.Announcement;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AnnouncementMapper {
    Announcement toRequestDtoMapEntity(AnnouncementRequestDto announcementRequestDto);
    AnnouncementResponseDto toEntityMapResponseDto(Announcement announcement);
    List<AnnouncementResponseDto> map(List<Announcement> announcement);
}
