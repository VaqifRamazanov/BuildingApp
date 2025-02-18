package com.matrix.buildingapp.controller;

import com.matrix.buildingapp.model.dto.requestDto.CardRequestDto;
import com.matrix.buildingapp.model.dto.responseDto.CardResponseDto;
import com.matrix.buildingapp.service.CardService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/card")
@RequiredArgsConstructor
public class CardController {
    private final CardService cardService;

    @PostMapping("/add/{id}")
    public ResponseEntity<CardResponseDto> add(@PathVariable Long id, @RequestBody @Valid CardRequestDto requestDto) {
        return new ResponseEntity<>(cardService.add(id,requestDto), HttpStatus.CREATED);
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<CardResponseDto> getById(@PathVariable Long id) {
        return new ResponseEntity<>(cardService.getById(id), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        cardService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
