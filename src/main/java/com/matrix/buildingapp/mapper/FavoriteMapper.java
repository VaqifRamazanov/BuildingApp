package com.matrix.buildingapp.mapper;

import com.matrix.buildingapp.model.dto.requestDto.FavoriteRequestDto;
import com.matrix.buildingapp.model.dto.responseDto.FavoriteResponseDto;
import com.matrix.buildingapp.model.entity.Favorite;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface FavoriteMapper {
    Favorite mapToEntity(FavoriteRequestDto requestDto);
    FavoriteResponseDto mapToResponse(Favorite favorite);
    void toUpdate(FavoriteRequestDto announcementDto, @MappingTarget Favorite favorite);
}
