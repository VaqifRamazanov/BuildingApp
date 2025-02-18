package com.matrix.buildingapp.model.dto.responseDto;

import lombok.Data;

@Data
public class LoginResponse {
    private String token;
    private String username;
}
