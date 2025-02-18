package com.matrix.buildingapp.controller;

import com.matrix.buildingapp.model.dto.requestDto.ConstructionCompanyRequestDto;
import com.matrix.buildingapp.model.dto.responseDto.AnnouncementResponseDto;
import com.matrix.buildingapp.model.dto.responseDto.CommentResponseDto;
import com.matrix.buildingapp.model.dto.responseDto.ConstructionCompanyResponseDto;
import com.matrix.buildingapp.service.ConstructionCompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
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
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ConstructionCompanyResponseDto> add(@RequestBody ConstructionCompanyRequestDto constructionCompanyRequestDto) {
        return ResponseEntity.ok(constructionCompanyService.add(constructionCompanyRequestDto));
    }

    @GetMapping({"/get/{id}"})
    public ResponseEntity<ConstructionCompanyResponseDto> getById(@PathVariable Integer id) {

        return ResponseEntity.ok(constructionCompanyService.findById(id));
    }

    @PutMapping("/update")
    public ResponseEntity<ConstructionCompanyResponseDto> update(@RequestBody ConstructionCompanyRequestDto constructionCompanyRequestDto) {
        return ResponseEntity.ok(constructionCompanyService.update(constructionCompanyRequestDto));
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
