package com.matrix.buildingapp.controller;

import com.matrix.buildingapp.model.dto.requestDto.AgencyRequestDto;
import com.matrix.buildingapp.model.dto.responseDto.AgencyResponseDto;
import com.matrix.buildingapp.model.dto.responseDto.AnnouncementResponseDto;
import com.matrix.buildingapp.service.AgencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping({"/agency"})
public class AgencyController {
    private final AgencyService agencyService;

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<AgencyResponseDto>  add(@RequestBody AgencyRequestDto agencyRequestDto) {

        return ResponseEntity.ok(agencyService.add(agencyRequestDto));
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<AgencyResponseDto> getById(@PathVariable Integer id) {

        return ResponseEntity.ok(agencyService.getById(id));
    }

    @PutMapping("/update")
    public ResponseEntity<AgencyResponseDto> update(@RequestBody AgencyRequestDto agencyRequestDto) {

        return ResponseEntity.ok(agencyService.update(agencyRequestDto));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Integer id) {

        agencyService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/agencies")
    public ResponseEntity <List<AgencyResponseDto>> getAll() {

        return  ResponseEntity.ok(agencyService.getAll());
    }

    @GetMapping("/{id}/announcements")
    public ResponseEntity<List<AnnouncementResponseDto>> getAnnouncementByAgency(@PathVariable Integer id){
        return ResponseEntity.ok(agencyService.getAnnouncementByAgency(id));
    }

}


