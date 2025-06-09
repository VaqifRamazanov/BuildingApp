package com.matrix.buildingapp.mapper;


import com.matrix.buildingapp.model.dto.requestDto.CommentRequestDto;
import com.matrix.buildingapp.model.dto.responseDto.CommentResponseDto;
import com.matrix.buildingapp.model.entity.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    Comment mapToEntity(CommentRequestDto commentRequestDto);

    CommentResponseDto mapToResponse(Comment comment);

    Comment map(CommentRequestDto commentRequestDto, @MappingTarget Comment comment);
}
