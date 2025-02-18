package com.matrix.buildingapp.model.dto.requestDto;

import lombok.Data;

@Data
public class ConstructionCompanyRequestDto {
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
