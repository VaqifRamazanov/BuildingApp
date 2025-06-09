package com.matrix.buildingapp.service.impl;


import com.matrix.buildingapp.exception.NotFoundException;
import com.matrix.buildingapp.mapper.AgencyMapper;
import com.matrix.buildingapp.mapper.AnnouncementMapper;
import com.matrix.buildingapp.model.dto.requestDto.AgencyRequestDto;
import com.matrix.buildingapp.model.dto.responseDto.AgencyResponseDto;
import com.matrix.buildingapp.model.dto.responseDto.AnnouncementResponseDto;
import com.matrix.buildingapp.model.entity.Agency;
import com.matrix.buildingapp.repository.AgencyRepository;
import com.matrix.buildingapp.service.AgencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AgencyServiceImpl implements AgencyService {
    private final AgencyRepository agencyRepository;
    private final AgencyMapper agencyMapper;
    private final AnnouncementMapper announcementMapper;
    @Override
    public AgencyResponseDto add(AgencyRequestDto agencyRequestDto) {
            Agency agency=agencyMapper.mapToEntity(agencyRequestDto);
            agencyRepository.save(agency);
             return agencyMapper.mapToResponse(agency);
    }

    @Override
    public AgencyResponseDto getById(Integer id) {
        Agency agency= agencyRepository.findById(id).orElseThrow(()-> new NotFoundException("agency not found"));
        return agencyMapper.mapToResponse(agency);

    }

    @Override
    public AgencyResponseDto update(Integer id  ,AgencyRequestDto agencyRequestDto) {
        Agency agency=agencyRepository.findById(id).orElseThrow(()-> new NotFoundException("agency not found"));
        Agency agency1= agencyMapper.map(agencyRequestDto,agency);
        agencyRepository.save(agency1);
        return agencyMapper.mapToResponse(agency1);

    }

    @Override
    public void delete(Integer id) {

        agencyRepository.deleteById(id);
    }

    @Override
    public List<AgencyResponseDto> getAll() {

        List<Agency> agencies= agencyRepository.findAll();
        return agencies.stream()
                .map(agencyMapper :: mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<AnnouncementResponseDto> getAnnouncementByAgency(Integer id){
    Agency agency=agencyRepository.findById(id).orElseThrow(()-> new NotFoundException("agency not found"));
        return agency.getAnnouncement().stream()
                .map(announcementMapper ::mapToResponse)
                .collect(Collectors.toList());

    }
}
