package com.matrix.buildingapp.model.dto.requestDto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class AgencyRequestDto {
    private String name;
    private String information;
    private String city;
    private String region;
    private String street;
    private String homeNumber;
    @NotBlank
    @Email
    private String mail;
    private String phoneNumber;
    private Boolean status;
}
