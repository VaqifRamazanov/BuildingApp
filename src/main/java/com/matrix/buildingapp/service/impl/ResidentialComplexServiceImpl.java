package com.matrix.buildingapp.service.impl;


import com.matrix.buildingapp.exception.NotFoundException;
import com.matrix.buildingapp.mapper.AnnouncementMapper;
import com.matrix.buildingapp.mapper.ResidentialComplexMapper;
import com.matrix.buildingapp.model.dto.requestDto.ResidentialComplexRequestDto;
import com.matrix.buildingapp.model.dto.responseDto.AnnouncementResponseDto;
import com.matrix.buildingapp.model.dto.responseDto.ResidentialComplexResponseDto;
import com.matrix.buildingapp.model.entity.ResidentialComplex;
import com.matrix.buildingapp.repository.AnnouncementRepository;
import com.matrix.buildingapp.repository.ResidentialComplexRepository;
import com.matrix.buildingapp.service.ResidentialComplexService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
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
        ResidentialComplex residentialComplex=residentialComplexMapper.mapToEntity(residentialComplexRequestDto);
        residentialComplexRepository.save(residentialComplex);
        return residentialComplexMapper.mapToResponse(residentialComplex);
    }

    @Override
    public ResidentialComplexResponseDto findById(Integer id) {

        ResidentialComplex residentialComplex= residentialComplexRepository.findById(id).orElseThrow(()->new NotFoundException("Residential Complex not found"));
        return residentialComplexMapper.mapToResponse(residentialComplex);

    }

    @Override
    public ResidentialComplexResponseDto update(Integer id  ,ResidentialComplexRequestDto residentialComplexRequestDto) {
        ResidentialComplex residentialComplex=residentialComplexRepository.findById(id).orElseThrow(()->new NotFoundException("residentialComplex not found"));
        ResidentialComplex residentialComplex1 =residentialComplexMapper.map(residentialComplexRequestDto,residentialComplex);
        residentialComplexRepository.save(residentialComplex1);
        return residentialComplexMapper.mapToResponse(residentialComplex1);

    }

    @Override
    public void delete(Integer id) {
        residentialComplexRepository.findById(id).orElseThrow(()-> new NotFoundException("residentialComplex not found"));
        residentialComplexRepository.deleteById(id);
    }

    @Override
    public List<ResidentialComplexResponseDto> findAll() {
        List<ResidentialComplex> residentialComplex= residentialComplexRepository.findAll();
        return residentialComplex.stream()
                .map(residentialComplexMapper :: mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<AnnouncementResponseDto> getAnnouncementByResidentialComplex(Integer id) {
log.info("Fetching announcement for Residential complex ID: {} ",id);
ResidentialComplex residentialComplex=residentialComplexRepository.findById(id)
        .orElseThrow(()->new NotFoundException("Residential complex not found"));
log.info("Fetching {} announcement for residential complex ID: {} ",residentialComplex.getAnnouncement().size(),id);
return residentialComplex.getAnnouncement().stream()
        .map(announcementMapper ::mapToResponse)
        .collect(Collectors.toList());
    }

}
