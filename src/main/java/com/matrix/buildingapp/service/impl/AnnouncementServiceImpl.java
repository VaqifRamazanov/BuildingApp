package com.matrix.buildingapp.service.impl;

import com.matrix.buildingapp.exception.NotFoundException;
import com.matrix.buildingapp.mapper.AnnouncementMapper;
import com.matrix.buildingapp.model.dto.requestDto.AnnouncementRequestDto;
import com.matrix.buildingapp.model.dto.responseDto.AnnouncementResponseDto;
import com.matrix.buildingapp.model.entity.*;
import com.matrix.buildingapp.repository.*;
import com.matrix.buildingapp.service.AnnouncementService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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

         Announcement announcement=announcementMapper.mapToEntity(announcementRequestDto);
        User user=userRepository.findById(announcementRequestDto.getUserId()).orElseThrow(()->new NotFoundException("User not found"));
        Agency agency=agencyRepository.findById(announcementRequestDto.getAgencyID()).orElseThrow(()->new NotFoundException("Agency not found"));
        ResidentialComplex residentialComplex=residentialComplexRepository.findById(announcementRequestDto.getResidentialComplexId()).orElseThrow(()->new NotFoundException("Residential complex not found"));
        ConstructionCompany constructionCompany=constructionCompanyRepository.findById(announcementRequestDto.getConstructionCompanyID()).orElseThrow(()->new NotFoundException("Construction company not found"));
        announcement.setUser(user);
        paymentService.paymentMethod(user,5);
        announcement.setAgency(agency);
        announcement.setResidentialComplex(residentialComplex);
        announcement.setConstructionCompany(constructionCompany);
        announcement.setAdditionDate(LocalDateTime.now());
         announcementRepository.save(announcement);
        AnnouncementResponseDto announcementResponseDto=announcementMapper.mapToResponse(announcement);
        announcementResponseDto.setAgencyID(agency.getId());
        announcementResponseDto.setConstructionCompanyID(constructionCompany.getId());
        announcementResponseDto.setUserId(user.getId());
        announcementResponseDto.setResidentialComplexId(residentialComplex.getId());
        return announcementResponseDto;
    }

    @Override
    public AnnouncementResponseDto getById(Integer id) {
        Announcement announcement= announcementRepository.findById(id).orElseThrow(()->new NotFoundException("announcement not found"));
          return announcementMapper.mapToResponse(announcement);

    }

    @Override
    public AnnouncementResponseDto update(Integer id,AnnouncementRequestDto announcementRequestDto) {
        Announcement announcement=announcementRepository.findById(id).orElseThrow(()-> new NotFoundException("announcement not found"));
        Announcement announcement1 = announcementMapper.map(announcementRequestDto,announcement);
        announcementRepository.save(announcement1);
        return announcementMapper.mapToResponse(announcement1);

    }

    @Override
    public void delete(Integer id) {
        announcementRepository.findById(id).orElseThrow(()-> new NotFoundException("announcement not found"));
        announcementRepository.deleteById(id);
    }

    @Override
    public List<AnnouncementResponseDto> getAll() {
        List<Announcement> announcements= announcementRepository.findAll();
        return announcements.stream()
                .map(announcementMapper :: mapToResponse)
                .collect(Collectors.toList());
    }
}
