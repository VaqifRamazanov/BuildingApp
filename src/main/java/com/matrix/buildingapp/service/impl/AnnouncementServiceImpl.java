package com.matrix.buildingapp.service.impl;

import com.matrix.buildingapp.exception.NotFoundException;
import com.matrix.buildingapp.mapper.AnnouncementMapper;
import com.matrix.buildingapp.model.dto.requestDto.AnnouncementRequestDto;
import com.matrix.buildingapp.model.dto.responseDto.AnnouncementResponseDto;
import com.matrix.buildingapp.model.entity.*;
import com.matrix.buildingapp.repository.*;
import com.matrix.buildingapp.service.AnnouncementService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AnnouncementServiceImpl implements AnnouncementService {
    private final AnnouncementRepository announcementRepository;
    private final AnnouncementMapper announcementMapper;
    private final UserRepository userRepository;
    private final AgencyRepository agencyRepository;
    private final ResidentialComplexRepository residentialComplexRepository;
    private final ConstructionCompanyRepository constructionCompanyRepository;
    private final PaymentServiceImpl paymentService;


    @Override
    public AnnouncementResponseDto add(AnnouncementRequestDto announcementRequestDto) {

         Announcement announcement=announcementMapper.toRequestDtoMapEntity(announcementRequestDto);
        User user=userRepository.findById(announcementRequestDto.getUserId()).orElseThrow(()->new NotFoundException("User not found"));
        Agency agency=agencyRepository.findById(announcementRequestDto.getAgencyID()).orElseThrow(()->new NotFoundException("Agency not found"));
        ResidentialComplex residentialComplex=residentialComplexRepository.findById(announcementRequestDto.getResidentialComplexId()).orElseThrow(()->new NotFoundException("Residential complex not found"));
        ConstructionCompany constructionCompany=constructionCompanyRepository.findById(announcementRequestDto.getConstructionCompanyID()).orElseThrow(()->new NotFoundException("Construction company not found"));
        announcement.setUser(user);
        paymentService.paymentMethod(user,5);
        announcement.setAgency(agency);
        announcement.setResidentialComplex(residentialComplex);
        announcement.setConstructionCompany(constructionCompany);


         announcementRepository.save(announcement);



        AnnouncementResponseDto announcementResponseDto =announcementMapper.toEntityMapResponseDto(announcement);
        return announcementResponseDto;
    }

    @Override
    public AnnouncementResponseDto getById(Integer id) {
        Announcement announcement= announcementRepository.findById(id).orElseThrow(NullPointerException::new);
        AnnouncementResponseDto announcementResponseDto =announcementMapper.toEntityMapResponseDto(announcement);
        return announcementResponseDto;

    }

    @Override
    public AnnouncementResponseDto update(AnnouncementRequestDto announcementRequestDto) {
        Announcement announcement= announcementMapper.toRequestDtoMapEntity(announcementRequestDto);
        announcementRepository.save(announcement);
        AnnouncementResponseDto announcementResponseDto =announcementMapper.toEntityMapResponseDto(announcement);
        return announcementResponseDto;
    }

    @Override
    public void delete(Integer id) {
        announcementRepository.deleteById(id);
    }

    @Override
    public List<AnnouncementResponseDto> getAll() {
        List<Announcement> announcements= announcementRepository.findAll();
        List <AnnouncementResponseDto> announcementResponseDto =announcementMapper
                .map(announcements);
        return announcementResponseDto;
    }
}
