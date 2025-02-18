package com.matrix.buildingapp.controller;

import com.matrix.buildingapp.model.dto.requestDto.LoginReq;
import com.matrix.buildingapp.model.dto.requestDto.UserRequestDto;
import com.matrix.buildingapp.model.dto.responseDto.LoginResponse;
import com.matrix.buildingapp.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/auth")
@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/sign-in")
    public LoginResponse login(@Valid @RequestBody LoginReq loginReq)  {
        return authService.login(loginReq);
    }


    @PostMapping("/register")
    public ResponseEntity<Void> register(@Valid @RequestBody UserRequestDto userReqDto)  {
        authService.register(userReqDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/forgetPassword")
    public ResponseEntity<Void> forgetPassword(String email){
        authService.forgetPassword(email);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(
            @RequestParam String email,
            @RequestParam String otp,
            @RequestParam String newPassword) {
        try {
            authService.resetPassword(email, otp, newPassword);
            return ResponseEntity.ok("Password reset successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


}
