package com.matrix.buildingapp.model.dto;

import lombok.Data;

@Data
public class UserDto {
    private String username;
    private String email;
    private String fullName;
    private String address;
    private String phoneNumber;
}
