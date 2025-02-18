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
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<AnnouncementResponseDto> add(@RequestBody AnnouncementRequestDto announcementRequestDto){
        return ResponseEntity.ok(announcementService.add(announcementRequestDto));
    }
    @GetMapping({"/get/{id}"})
    public ResponseEntity<AnnouncementResponseDto> getById(@PathVariable Integer id){

        return ResponseEntity.ok(announcementService.getById(id));
    }
    @PutMapping("/update")
    public ResponseEntity<AnnouncementResponseDto> update(@RequestBody AnnouncementRequestDto announcementRequestDto){

        return ResponseEntity.ok(announcementService.update(announcementRequestDto));
    }

    @DeleteMapping({"/delete/{id}"})
    public ResponseEntity<Void> deleteById(@PathVariable Integer id){

        announcementService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @GetMapping("/getAll")
    public  ResponseEntity<List<AnnouncementResponseDto>> getAll()
    {
      return  ResponseEntity.ok(announcementService.getAll());

    }
}
