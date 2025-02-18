package com.matrix.buildingapp.service;



import com.matrix.buildingapp.model.dto.requestDto.CommentRequestDto;
import com.matrix.buildingapp.model.dto.responseDto.CommentResponseDto;
import org.springframework.data.domain.Page;

import java.util.List;


public interface CommentService {
    CommentResponseDto add(CommentRequestDto commentRequestDto);
    CommentResponseDto getById(Integer id);
    CommentResponseDto update(CommentRequestDto commentRequestDto);
    void delete(Integer id);
    List<CommentResponseDto> getAll();

}
