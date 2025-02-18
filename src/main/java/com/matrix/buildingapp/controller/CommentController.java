package com.matrix.buildingapp.controller;

import com.matrix.buildingapp.model.dto.requestDto.CommentRequestDto;
import com.matrix.buildingapp.model.dto.responseDto.CommentResponseDto;
import com.matrix.buildingapp.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.aop.scope.ScopedObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping({"/comment"})
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<CommentResponseDto> add(@RequestBody CommentRequestDto commentRequestDto){
        return ResponseEntity.ok(commentService.add(commentRequestDto));
    }
    @GetMapping({"/get/{id}"})
    public ResponseEntity<CommentResponseDto> getById(@PathVariable Integer id){
        return ResponseEntity.ok(commentService.getById(id));
    }
    @PutMapping("/update")
    public ResponseEntity<CommentResponseDto> update(@RequestBody CommentRequestDto commentRequestDto){
        return ResponseEntity.ok(commentService.update(commentRequestDto));

    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Integer id){
        commentService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
