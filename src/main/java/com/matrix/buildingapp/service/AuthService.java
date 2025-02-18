package com.matrix.buildingapp.service;

import com.matrix.buildingapp.model.dto.requestDto.LoginReq;
import com.matrix.buildingapp.model.dto.requestDto.UserRequestDto;
import com.matrix.buildingapp.model.dto.responseDto.LoginResponse;

public interface AuthService {
    void register(UserRequestDto userRequestDto);
    LoginResponse login(LoginReq loginReq);
    void forgetPassword(String email);
    void resetPassword(String email,String otp,String newPassword);

}
