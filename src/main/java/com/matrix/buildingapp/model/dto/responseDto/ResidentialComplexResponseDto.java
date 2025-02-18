package com.matrix.buildingapp.model.dto.responseDto;

import lombok.Data;

@Data
public class ResidentialComplexResponseDto {
    private Integer id ;
    private String name;
    private String city;
    private String region;
    private String street;
    private String homeNumber;
    private String deliveryTime;
    private Integer blockNumber;
    private Integer totalApartmentNumber;
    private Integer numberApartmentsEachFloor;
    private Integer floorNumber;
    private String conditions;
    private Integer elevatorNumber;
}
