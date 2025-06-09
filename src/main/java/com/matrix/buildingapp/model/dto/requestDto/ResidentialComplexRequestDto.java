package com.matrix.buildingapp.model.dto.requestDto;

import jakarta.validation.constraints.Max;
import lombok.Data;

@Data
public class ResidentialComplexRequestDto {
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
