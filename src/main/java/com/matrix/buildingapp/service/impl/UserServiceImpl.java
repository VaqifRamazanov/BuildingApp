package com.matrix.buildingapp.service.impl;

import com.matrix.buildingapp.exception.NotFoundException;
import com.matrix.buildingapp.mapper.AnnouncementMapper;
import com.matrix.buildingapp.mapper.CardMapper;
import com.matrix.buildingapp.mapper.UserMapper;
import com.matrix.buildingapp.model.dto.UserDto;
import com.matrix.buildingapp.model.dto.responseDto.AnnouncementResponseDto;
import com.matrix.buildingapp.model.dto.responseDto.CardResponseDto;
import com.matrix.buildingapp.model.dto.responseDto.UserResponseDto;
import com.matrix.buildingapp.model.entity.User;
import com.matrix.buildingapp.repository.AnnouncementRepository;
import com.matrix.buildingapp.repository.UserRepository;
import com.matrix.buildingapp.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final AnnouncementRepository announcementRepository;
    private final AnnouncementMapper announcementMapper;
    private final CardMapper cardMapper;
    @Override
    public UserResponseDto getById(Long id) {
        log.info("Fetching user by ID: {}",id);
        return modelMapper.map(userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("user not found")),UserResponseDto.class);

    }

    @Override
    public UserResponseDto update(Long id, UserDto userDto) {
        log.info("Updating user with ID:{}",id);
        User user=userRepository.findById(id).orElseThrow();
        userMapper.toUpdate(userDto,user);
        user.setUpdatedAt(LocalDateTime.now());
        User ans =userRepository.save(user);
        log.info("User with ID {} updated successfully",id);
        return userMapper.mapToResponse(ans);
    }

    @Override
    public void delete(Long id) {
    log.info("Deleting user with ID: {}",id);
    userRepository.deleteById(id);
    log.info("User with ID {} deleted successfully",id);
    }

    @Override
    public List<UserResponseDto> getAll() {
        log.info("Fetching all users");
        return userRepository.findAll().stream().map((user)->userMapper.mapToResponse(user)).collect(Collectors.toList());
    }

    @Override
    public List<AnnouncementResponseDto> getAnnouncementByUser(Long id) {
        log.info("Fetching announcement for User ID: {}",id);
        User user=userRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("User not found"));
        List<AnnouncementResponseDto> announcements=announcementRepository.findByUser(user)
                .stream().map(announcement -> announcementMapper.mapToResponse(announcement))
                .collect(Collectors.toList());
        log.info("Fetching {} announcement for user ID: {} ",announcements.size(),id);
        return announcements;
    }

    @Override
    public CardResponseDto getCardByUser(Long id) {
        log.info("Fetching card for User ID: {}",id);
        User user=userRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("User not found"));
       CardResponseDto cardResponseDto=cardMapper.mapToResponse(user.getCard());
       return cardResponseDto;

    }
}
