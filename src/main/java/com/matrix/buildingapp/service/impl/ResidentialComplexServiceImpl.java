package com.matrix.buildingapp.service.impl;


import com.matrix.buildingapp.exception.NotFoundException;
import com.matrix.buildingapp.mapper.AnnouncementMapper;
import com.matrix.buildingapp.mapper.ResidentialComplexMapper;
import com.matrix.buildingapp.model.dto.requestDto.ResidentialComplexRequestDto;
import com.matrix.buildingapp.model.dto.responseDto.AnnouncementResponseDto;
import com.matrix.buildingapp.model.dto.responseDto.ResidentialComplexResponseDto;
import com.matrix.buildingapp.model.entity.ConstructionCompany;
import com.matrix.buildingapp.model.entity.ResidentialComplex;
import com.matrix.buildingapp.repository.AnnouncementRepository;
import com.matrix.buildingapp.repository.ResidentialComplexRepository;
import com.matrix.buildingapp.service.ResidentialComplexService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Data
@Slf4j
public class ResidentialComplexServiceImpl implements ResidentialComplexService {
    private final ResidentialComplexRepository residentialComplexRepository;
    private final ResidentialComplexMapper residentialComplexMapper;
    private final AnnouncementRepository announcementRepository;
    private final AnnouncementMapper announcementMapper;

    @Override
    public ResidentialComplexResponseDto add(ResidentialComplexRequestDto residentialComplexRequestDto) {
        ResidentialComplex residentialComplex=residentialComplexMapper.toRequestDtoMapEntity(residentialComplexRequestDto);
        residentialComplexRepository.save(residentialComplex);
        ResidentialComplexResponseDto residentialComplexResponseDto =residentialComplexMapper.toEntityMapResponseDto(residentialComplex);
        return residentialComplexResponseDto;
    }

    @Override
    public ResidentialComplexResponseDto findById(Integer id) {

        ResidentialComplex residentialComplex= residentialComplexRepository.findById(id).orElseThrow(()->new NotFoundException("Residential Complex not found"));
        ResidentialComplexResponseDto residentialComplexResponseDto =residentialComplexMapper.toEntityMapResponseDto(residentialComplex);
        return residentialComplexResponseDto;
    }

    @Override
    public ResidentialComplexResponseDto update(ResidentialComplexRequestDto residentialComplexRequestDto) {
        ResidentialComplex residentialComplex=residentialComplexMapper.toRequestDtoMapEntity(residentialComplexRequestDto);
        residentialComplexRepository.save(residentialComplex);
        ResidentialComplexResponseDto residentialComplexResponseDto =residentialComplexMapper.toEntityMapResponseDto(residentialComplex);
        return residentialComplexResponseDto;
    }

    @Override
    public void delete(Integer id) {

        residentialComplexRepository.deleteById(id);
    }

    @Override
    public List<ResidentialComplexResponseDto> findAll() {
        List<ResidentialComplex> residentialComplex= residentialComplexRepository.findAll();
        List<ResidentialComplexResponseDto> residentialComplexResponseDto =residentialComplexMapper.map(residentialComplex);
        return residentialComplexResponseDto;
    }

    @Override
    public List<AnnouncementResponseDto> getAnnouncementByResidentialComplex(Integer id) {
log.info("Fetching announcement for Residential complex ID: {} ",id);
ResidentialComplex residentialComplex=residentialComplexRepository.findById(id)
        .orElseThrow(()->new NotFoundException("Residential complex not found"));
List<AnnouncementResponseDto> announcements=announcementRepository.findByResidentialComplex(residentialComplex)
        .stream().map(announcement -> announcementMapper.toEntityMapResponseDto(announcement))
        .collect(Collectors.toList());
log.info("Fetching {} announcement for residential complex ID: {} ",announcements.size(),id);
return announcements;
    }

}
