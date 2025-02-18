package com.matrix.buildingapp.service.impl;


import com.matrix.buildingapp.mapper.AgencyMapper;
import com.matrix.buildingapp.mapper.AnnouncementMapper;
import com.matrix.buildingapp.model.dto.requestDto.AgencyRequestDto;
import com.matrix.buildingapp.model.dto.responseDto.AgencyResponseDto;
import com.matrix.buildingapp.model.dto.responseDto.AnnouncementResponseDto;
import com.matrix.buildingapp.model.entity.Agency;
import com.matrix.buildingapp.repository.AgencyRepository;
import com.matrix.buildingapp.repository.AnnouncementRepository;
import com.matrix.buildingapp.service.AgencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AgencyServiceImpl implements AgencyService {
    private final AgencyRepository agencyRepository;
    private final AgencyMapper agencyMapper;
    private final AnnouncementRepository announcementRepository;
    private final AnnouncementMapper announcementMapper;
    @Override
    public AgencyResponseDto add(AgencyRequestDto agencyRequestDto) {
            Agency agency=agencyMapper.toRequestDtoMapEntity(agencyRequestDto);
            agencyRepository.save(agency);
            AgencyResponseDto agencyResponseDto=agencyMapper.toEntityMapResponseDto(agency);
            return agencyResponseDto;
    }

    @Override
    public AgencyResponseDto getById(Integer id) {
        Agency agency= agencyRepository.findById(id).orElseThrow(NullPointerException::new);
        AgencyResponseDto agencyResponseDto =agencyMapper.toEntityMapResponseDto(agency);
        return agencyResponseDto;
    }

    @Override
    public AgencyResponseDto update(AgencyRequestDto agencyRequestDto) {
        Agency agency= agencyMapper.toRequestDtoMapEntity(agencyRequestDto);
        agencyRepository.save(agency);
        AgencyResponseDto agencyResponseDto =agencyMapper.toEntityMapResponseDto(agency);
        return agencyResponseDto;
    }

    @Override
    public void delete(Integer id) {

        agencyRepository.deleteById(id);
    }

    @Override
    public List<AgencyResponseDto> getAll() {

        List<Agency> agencies= agencyRepository.findAll();
        List<AgencyResponseDto> agencyResponseDto =agencyMapper.map(agencies);
        return agencyResponseDto;
    }

    @Override
    public List<AnnouncementResponseDto> getAnnouncementByAgency(Integer id){
    Agency agency=agencyRepository.getById(id);
    List<AnnouncementResponseDto> announcementResponseDto
            =announcementRepository.findByAgency(agency).stream()
            .map(announcement -> announcementMapper.toEntityMapResponseDto(announcement))
            .collect(Collectors.toList());
    return announcementResponseDto;

    }
}
