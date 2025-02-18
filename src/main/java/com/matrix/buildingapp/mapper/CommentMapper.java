package com.matrix.buildingapp.mapper;


import com.matrix.buildingapp.model.dto.requestDto.CommentRequestDto;
import com.matrix.buildingapp.model.dto.responseDto.CommentResponseDto;
import com.matrix.buildingapp.model.entity.Comment;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    Comment toRequestDtoMapEntity(CommentRequestDto commentRequestDto);

    CommentResponseDto toEntityMapResponseDto(Comment comment);

    List<CommentResponseDto> map(List<Comment> comment);
}
