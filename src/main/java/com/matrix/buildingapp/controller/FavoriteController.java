package com.matrix.buildingapp.controller;

import com.matrix.buildingapp.model.dto.requestDto.FavoriteRequestDto;
import com.matrix.buildingapp.model.dto.responseDto.AnnouncementResponseDto;
import com.matrix.buildingapp.service.FavoriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequiredArgsConstructor
@RequestMapping({"/favorite"})
public class FavoriteController {
    private final FavoriteService favoriteService;

    @PostMapping("/add")
    public ResponseEntity<Void> add(@RequestBody FavoriteRequestDto favoriteRequestDto){
       favoriteService.add(favoriteRequestDto);
       return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteById(@RequestBody FavoriteRequestDto favoriteRequestDto){
        favoriteService.delete(favoriteRequestDto);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @GetMapping("/getAll/{userId}")
    public ResponseEntity<List<AnnouncementResponseDto>> getAll(@PathVariable Long userId) {

        return ResponseEntity.ok(favoriteService.getAll(userId));
    }
}
