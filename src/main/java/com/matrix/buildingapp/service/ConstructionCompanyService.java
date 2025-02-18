package com.matrix.buildingapp.service;



import com.matrix.buildingapp.model.dto.requestDto.ConstructionCompanyRequestDto;
import com.matrix.buildingapp.model.dto.responseDto.AnnouncementResponseDto;
import com.matrix.buildingapp.model.dto.responseDto.CommentResponseDto;
import com.matrix.buildingapp.model.dto.responseDto.ConstructionCompanyResponseDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ConstructionCompanyService {
    ConstructionCompanyResponseDto add(ConstructionCompanyRequestDto constructionCompanyRequestDto);
    ConstructionCompanyResponseDto findById(Integer id);
    ConstructionCompanyResponseDto update(ConstructionCompanyRequestDto constructionCompanyRequestDto);
    void delete(Integer id);
    List<ConstructionCompanyResponseDto> findAll();
    List<AnnouncementResponseDto> getAnnouncementByConstructionCompany(Integer id);
    List<CommentResponseDto> getCommentBYConstructionCompany(Integer id);
}
