package com.matrix.buildingapp.service.impl;

import com.matrix.buildingapp.exception.NotFoundException;
import com.matrix.buildingapp.mapper.AnnouncementMapper;
import com.matrix.buildingapp.mapper.CommentMapper;
import com.matrix.buildingapp.mapper.ConstructionCompanyMapper;
import com.matrix.buildingapp.model.dto.requestDto.ConstructionCompanyRequestDto;
import com.matrix.buildingapp.model.dto.responseDto.AnnouncementResponseDto;
import com.matrix.buildingapp.model.dto.responseDto.CommentResponseDto;
import com.matrix.buildingapp.model.dto.responseDto.ConstructionCompanyResponseDto;
import com.matrix.buildingapp.model.entity.ConstructionCompany;
import com.matrix.buildingapp.repository.AnnouncementRepository;
import com.matrix.buildingapp.repository.CommentRepository;
import com.matrix.buildingapp.repository.ConstructionCompanyRepository;
import com.matrix.buildingapp.service.ConstructionCompanyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ConstructionCompanyServiceImpl implements ConstructionCompanyService {
    private final ConstructionCompanyMapper constructionCompanyMapper;
    private final ConstructionCompanyRepository constructionCompanyRepository;
    private final AnnouncementRepository announcementRepository;
    private final AnnouncementMapper announcementMapper;
    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;

    @Override
    public ConstructionCompanyResponseDto add(ConstructionCompanyRequestDto constructionCompanyRequestDto) {
        ConstructionCompany constructionCompany = constructionCompanyMapper.
                toRequestDtoMapEntity(constructionCompanyRequestDto);
        constructionCompanyRepository.save(constructionCompany);
        ConstructionCompanyResponseDto constructionCompanyResponseDto = constructionCompanyMapper
                .toEntityMapResponseDto(constructionCompany);
        return constructionCompanyResponseDto;
    }

    @Override
    public ConstructionCompanyResponseDto findById(Integer id) {
        ConstructionCompany constructionCompany = constructionCompanyRepository.
                findById(id).orElseThrow(NullPointerException::new);
        ConstructionCompanyResponseDto constructionCompanyResponseDto = constructionCompanyMapper
                .toEntityMapResponseDto(constructionCompany);
        return constructionCompanyResponseDto;
    }

    @Override
    public ConstructionCompanyResponseDto update(ConstructionCompanyRequestDto constructionCompanyRequestDto) {
        ConstructionCompany constructionCompany = constructionCompanyMapper.
                toRequestDtoMapEntity(constructionCompanyRequestDto);
        constructionCompanyRepository.save(constructionCompany);
        ConstructionCompanyResponseDto constructionCompanyResponseDto = constructionCompanyMapper
                .toEntityMapResponseDto(constructionCompany);
        return constructionCompanyResponseDto;
    }

    @Override
    public void delete(Integer id) {
        constructionCompanyRepository.deleteById(id);

    }

    @Override
    public List<ConstructionCompanyResponseDto> findAll() {
        List<ConstructionCompany> constructionCompanies = constructionCompanyRepository.findAll();
        List<ConstructionCompanyResponseDto> constructionCompanyResponseDtos =
                constructionCompanyMapper.map(constructionCompanies);
        return constructionCompanyResponseDtos;
    }

    @Override
    public List<AnnouncementResponseDto> getAnnouncementByConstructionCompany(Integer id) {
        log.info("Fetching announcement for Construction company ID: {} ",id);
        ConstructionCompany constructionCompany=constructionCompanyRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("Construction company not found"));
        List<AnnouncementResponseDto> announcements=announcementRepository.findByConstructionCompany(constructionCompany)
                .stream().map(announcement -> announcementMapper.toEntityMapResponseDto(announcement))
                .collect(Collectors.toList());
        log.info("Fetching {} announcement for construction company ID:{}",announcements.size(),id);
        return announcements;
    }

    @Override
    public List<CommentResponseDto> getCommentBYConstructionCompany(Integer id) {
        log.info("Fetching comment for Construction company ID: {} ",id);
        ConstructionCompany constructionCompany=constructionCompanyRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("Construction company not found"));
        List<CommentResponseDto> comments=commentRepository.findByConstructionCompany(constructionCompany).stream()
                .map(comment-> commentMapper.toEntityMapResponseDto(comment))
                .collect(Collectors.toList());
        log.info("Fetching {} comments for Construction company ID: {}",comments.size(),id);
        return comments;

    }


}
