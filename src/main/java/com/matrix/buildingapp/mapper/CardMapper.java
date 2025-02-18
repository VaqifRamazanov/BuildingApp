package com.matrix.buildingapp.mapper;

import com.matrix.buildingapp.model.dto.requestDto.CardRequestDto;
import com.matrix.buildingapp.model.dto.responseDto.CardResponseDto;
import com.matrix.buildingapp.model.entity.Card;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CardMapper {
    Card toEntity(CardRequestDto cardRequestDto);
    CardResponseDto toResponse(Card card);
    void toUpdate(CardRequestDto cardRequestDto, @MappingTarget Card card);

}
