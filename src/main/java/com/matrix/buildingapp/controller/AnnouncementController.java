package com.matrix.buildingapp.controller;

import com.matrix.buildingapp.model.dto.requestDto.AnnouncementRequestDto;
import com.matrix.buildingapp.model.dto.responseDto.AnnouncementResponseDto;
import com.matrix.buildingapp.service.AnnouncementService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping({"/announcement"})
public class AnnouncementController {
    private final AnnouncementService announcementService;
    @PostMapping("/add")
    public ResponseEntity<AnnouncementResponseDto> add(@ModelAttribute AnnouncementRequestDto announcementRequestDto){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(announcementService.add(announcementRequestDto));
    }
    @GetMapping({"/get/{id}"})
    public ResponseEntity<AnnouncementResponseDto> getById(@PathVariable Integer id){

        return ResponseEntity.status(HttpStatus.OK)
                .body(announcementService.getById(id));
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<AnnouncementResponseDto> update(@PathVariable Integer id ,@ModelAttribute AnnouncementRequestDto announcementRequestDto){

        return ResponseEntity.status(HttpStatus.OK)
                .body(announcementService.update(id,announcementRequestDto));
    }

    @DeleteMapping({"/delete/{id}"})
    public ResponseEntity<Void> deleteById(@PathVariable Integer id){

        announcementService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @GetMapping("/getAll")
    public  ResponseEntity<List<AnnouncementResponseDto>> getAll()
    {
      return  ResponseEntity.status(HttpStatus.OK)
              .body(announcementService.getAll());

    }
}
