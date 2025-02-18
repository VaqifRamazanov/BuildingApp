package com.matrix.buildingapp.model.dto.responseDto;

import lombok.Data;

@Data
public class AgencyResponseDto {
    private Integer id ;
    private String name;
    private String information;
    private String city;
    private String region;
    private String street;
    private String homeNumber;
    private String mail;
    private String phoneNumber;
    private Boolean status;
}
