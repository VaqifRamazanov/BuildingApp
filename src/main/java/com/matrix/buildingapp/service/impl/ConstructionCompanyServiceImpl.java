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
import com.matrix.buildingapp.repository.ConstructionCompanyRepository;
import com.matrix.buildingapp.service.ConstructionCompanyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ConstructionCompanyServiceImpl implements ConstructionCompanyService {
    private final ConstructionCompanyMapper constructionCompanyMapper;
    private final ConstructionCompanyRepository constructionCompanyRepository;
    private final AnnouncementMapper announcementMapper;
    private final CommentMapper commentMapper;

    @Override
    public ConstructionCompanyResponseDto add(ConstructionCompanyRequestDto constructionCompanyRequestDto) {
        ConstructionCompany constructionCompany = constructionCompanyMapper.
                mapToEntity(constructionCompanyRequestDto);
        constructionCompanyRepository.save(constructionCompany);
        return constructionCompanyMapper.mapToResponse(constructionCompany);
    }

    @Override
    public ConstructionCompanyResponseDto findById(Integer id) {
        ConstructionCompany constructionCompany = constructionCompanyRepository.
                findById(id).orElseThrow(()-> new NotFoundException("construction company not found"));
        return constructionCompanyMapper.mapToResponse(constructionCompany);
    }

    @Override
    public ConstructionCompanyResponseDto update(Integer id,ConstructionCompanyRequestDto constructionCompanyRequestDto) {
        ConstructionCompany constructionCompany=constructionCompanyRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("construction company not found"));
        ConstructionCompany constructionCompany1 = constructionCompanyMapper.
                map(constructionCompanyRequestDto,constructionCompany);
        constructionCompanyRepository.save(constructionCompany1);
       return constructionCompanyMapper.mapToResponse(constructionCompany1);
    }

    @Override
    public void delete(Integer id) {
        constructionCompanyRepository.findById(id).orElseThrow(()-> new NotFoundException("construction company not found"));
        constructionCompanyRepository.deleteById(id);

    }

    @Override
    public List<ConstructionCompanyResponseDto> findAll() {
        List<ConstructionCompany> constructionCompanies = constructionCompanyRepository.findAll();
        return constructionCompanies.stream()
                .map(constructionCompanyMapper ::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<AnnouncementResponseDto> getAnnouncementByConstructionCompany(Integer id) {
        log.info("Fetching announcement for Construction company ID: {} ",id);
        ConstructionCompany constructionCompany=constructionCompanyRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("Construction company not found"));
        log.info("Fetching {} announcement for construction company ID:{}",constructionCompany.getAnnouncement().size(),id);
        return constructionCompany.getAnnouncement().stream()
                .map(announcementMapper ::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<CommentResponseDto> getCommentBYConstructionCompany(Integer id) {
        log.info("Fetching comment for Construction company ID: {} ",id);
        ConstructionCompany constructionCompany=constructionCompanyRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("Construction company not found"));
        log.info("Fetching {} comments for Construction company ID: {}",constructionCompany.getComment().size(),id);
        return constructionCompany.getComment().stream()
                .map(commentMapper :: mapToResponse)
                .collect(Collectors.toList());

    }


}
