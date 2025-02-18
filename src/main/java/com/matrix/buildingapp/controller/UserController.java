package com.matrix.buildingapp.controller;

import com.matrix.buildingapp.model.dto.UserDto;
import com.matrix.buildingapp.model.dto.responseDto.AnnouncementResponseDto;
import com.matrix.buildingapp.model.dto.responseDto.CardResponseDto;
import com.matrix.buildingapp.model.dto.responseDto.UserResponseDto;
import com.matrix.buildingapp.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;


    @Operation(summary = "Get only one User with id")
    @GetMapping("/get/{id}")
    public ResponseEntity<UserResponseDto> getById(@PathVariable Long id) {
        return new ResponseEntity<>(userService.getById(id), HttpStatus.OK);
    }

    @Operation(summary = "Update User")
    @PutMapping("/update/{id}")
    public ResponseEntity<UserResponseDto> update(@PathVariable Long id,@RequestBody UserDto userDto) {
        return new ResponseEntity<>(userService.update(id,userDto), HttpStatus.OK);

    }

    @Operation(summary = "Delete User")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "get all Users")
    @GetMapping("/getAll")
    public ResponseEntity<List<UserResponseDto>> getAll() {
        return ResponseEntity.ok(userService.getAll());
    }

    @GetMapping("/{id}/announcements")
    public ResponseEntity<List<AnnouncementResponseDto>> getAnnouncementByUser(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getAnnouncementByUser(id));
    }

    @GetMapping("/{id}/cards")
    public ResponseEntity<CardResponseDto> getCardByUser(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getCardByUser(id));
    }
}
