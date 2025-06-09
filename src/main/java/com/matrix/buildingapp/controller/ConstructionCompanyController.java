package com.matrix.buildingapp.controller;

import com.matrix.buildingapp.model.dto.requestDto.ConstructionCompanyRequestDto;
import com.matrix.buildingapp.model.dto.responseDto.AnnouncementResponseDto;
import com.matrix.buildingapp.model.dto.responseDto.CommentResponseDto;
import com.matrix.buildingapp.model.dto.responseDto.ConstructionCompanyResponseDto;
import com.matrix.buildingapp.service.ConstructionCompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping({"/constructionCompany"})
public class ConstructionCompanyController {
    private final ConstructionCompanyService constructionCompanyService;

    @PostMapping("/add")
    public ResponseEntity<ConstructionCompanyResponseDto> add(@RequestBody ConstructionCompanyRequestDto constructionCompanyRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(constructionCompanyService.add(constructionCompanyRequestDto));
    }

    @GetMapping({"/get/{id}"})
    public ResponseEntity<ConstructionCompanyResponseDto> getById(@PathVariable Integer id) {

        return ResponseEntity.status(HttpStatus.OK)
                .body(constructionCompanyService.findById(id));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ConstructionCompanyResponseDto> update(@PathVariable Integer id,@RequestBody ConstructionCompanyRequestDto constructionCompanyRequestDto) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(constructionCompanyService.update(id, constructionCompanyRequestDto));
    }

    @DeleteMapping({"/{id}"})
    public ResponseEntity<Void> deleteById(@PathVariable Integer id) {
        constructionCompanyService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<ConstructionCompanyResponseDto>> getAll() {

        return ResponseEntity.ok(constructionCompanyService.findAll());
    }

    @GetMapping("/{id}/announcements")
    public ResponseEntity<List<AnnouncementResponseDto>> getAnnouncementByConstructionCompany(@PathVariable Integer id) {
        return ResponseEntity.ok(constructionCompanyService.getAnnouncementByConstructionCompany(id));
    }

    @GetMapping("/{id}/comments")
    public ResponseEntity<List<CommentResponseDto>> getCommentByConstructionCompany(@PathVariable Integer id){
        return ResponseEntity.ok(constructionCompanyService.getCommentBYConstructionCompany(id));
    }
}
