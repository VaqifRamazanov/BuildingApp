package com.matrix.buildingapp.service.impl;

import com.matrix.buildingapp.exception.NotFoundException;
import com.matrix.buildingapp.mapper.AnnouncementMapper;
import com.matrix.buildingapp.mapper.FavoriteMapper;
import com.matrix.buildingapp.mapper.UserMapper;
import com.matrix.buildingapp.model.dto.requestDto.FavoriteRequestDto;
import com.matrix.buildingapp.model.dto.responseDto.AnnouncementResponseDto;
import com.matrix.buildingapp.model.entity.Announcement;
import com.matrix.buildingapp.model.entity.Favorite;
import com.matrix.buildingapp.model.entity.User;
import com.matrix.buildingapp.repository.AnnouncementRepository;
import com.matrix.buildingapp.repository.FavoriteRepository;
import com.matrix.buildingapp.repository.UserRepository;
import com.matrix.buildingapp.service.FavoriteService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FavoriteServiceImpl implements FavoriteService {
    private final FavoriteMapper favoriteMapper;
    private final FavoriteRepository favoriteRepository;
    private final AnnouncementRepository announcementRepository;
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final AnnouncementMapper announcementMapper;

    @Override
    @Transactional
    public void add(FavoriteRequestDto requestDto) {
        Announcement announcement=announcementRepository.findById(Math.toIntExact(requestDto.getAnnouncementId()))
                .orElseThrow(()-> new NotFoundException("Not Found as Like Announcement"));
        User user=userRepository.findById(requestDto.getUserId()).orElseThrow(()-> new NotFoundException("User not found")) ;

        if (user.getFavorite()==null){
            user.setFavorite(new Favorite());
        }
        Favorite favorite=user.getFavorite();
        if (favorite.getAnnouncement()==null  || favorite.getAnnouncement().size() == 0 ){
            favorite.setAnnouncement(new ArrayList<Announcement>());
        }
        List<Announcement> announcements=favorite.getAnnouncement();
        announcements.add(announcement);
        favorite.setUser(user);
        favoriteRepository.save(favorite);


    }

    @Override
    public void delete(FavoriteRequestDto requestDto) {
        User user =userRepository.findById(requestDto.getUserId())
                .orElseThrow(()->new NotFoundException("User not found"));
        Announcement announcement=announcementRepository.findById(requestDto.getAnnouncementId().intValue())
                .orElseThrow(()->new NotFoundException("Not found as like Product"));
        Favorite favorite=user.getFavorite();
        favorite.getAnnouncement().remove(announcement);
        favoriteRepository.save(favorite);

    }

    @Override
    public List<AnnouncementResponseDto> getAll(Long id) {
        User user=userRepository.findById(id).orElseThrow(()-> new NotFoundException("User not found"));
        if (user.getFavorite()==null || user.getFavorite().getAnnouncement().size()==0){
            throw new NotFoundException("You dont have announcement in favorite");
        }
        Favorite favorite=favoriteRepository.findByUser(user);
        return announcementMapper.map(favorite.getAnnouncement());
    }
}
