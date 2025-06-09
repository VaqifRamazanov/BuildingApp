package com.matrix.buildingapp.controller;

import com.matrix.buildingapp.model.dto.requestDto.ResidentialComplexRequestDto;
import com.matrix.buildingapp.model.dto.responseDto.AnnouncementResponseDto;
import com.matrix.buildingapp.model.dto.responseDto.ResidentialComplexResponseDto;
import com.matrix.buildingapp.service.ResidentialComplexService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping({"/residentialComplex"})
public class ResidentialComplexController {
    private final ResidentialComplexService residentialComplexService;

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ResidentialComplexResponseDto> add(@RequestBody ResidentialComplexRequestDto legalComplexRequestDto) {
        return ResponseEntity.ok(residentialComplexService.add(legalComplexRequestDto));
    }

    @GetMapping({"/get/{id}"})
    public ResponseEntity<ResidentialComplexResponseDto> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(residentialComplexService.findById(id));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ResidentialComplexResponseDto> Update(@PathVariable Integer id,@RequestBody ResidentialComplexRequestDto legalComplexRequestDto) {

        return ResponseEntity.ok(residentialComplexService.update(id,legalComplexRequestDto));
    }

    @DeleteMapping({"/delete/{id}"})
    public ResponseEntity<Void> deleteById(@PathVariable Integer id) {

        residentialComplexService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<ResidentialComplexResponseDto>> getAll() {

        return ResponseEntity.ok(residentialComplexService.findAll());
    }

    @GetMapping("/{id}/announcements")
    public ResponseEntity<List<AnnouncementResponseDto>> getAnnouncementByResidentialComplex(@PathVariable Integer id) {
        return ResponseEntity.ok(residentialComplexService.getAnnouncementByResidentialComplex(id));
    }
}
