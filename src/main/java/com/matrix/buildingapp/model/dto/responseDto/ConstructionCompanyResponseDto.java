package com.matrix.buildingapp.model.dto.responseDto;

import lombok.Data;

@Data
public class ConstructionCompanyResponseDto {
    private Integer id;
    private String name;
    private String information;
    private String city;
    private String region;
    private String street;
    private String homeNumber;
    private String phoneNumber;
    private String executionPeriod;
    private String serviceName;
    private String price;
}
