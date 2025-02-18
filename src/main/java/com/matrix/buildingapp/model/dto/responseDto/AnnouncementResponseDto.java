package com.matrix.buildingapp.model.dto.responseDto;

import com.matrix.buildingapp.model.entity.Agency;
import com.matrix.buildingapp.model.entity.ConstructionCompany;
import com.matrix.buildingapp.model.entity.ResidentialComplex;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;
@Data
public class AnnouncementResponseDto {
    private Integer id ;
    private String area;
    private String announcementType;
    private String announcementForm;
    private Long price;
    private String information;
    private Integer roomNumber;
    private Boolean repaired;
    private Boolean titleDeed;
    private Boolean mortgage;
    private Integer floorNumber;
    private String city;
    private String region;
    private String street;
    private String homeNumber;
    private String constructionDate;
    private LocalDateTime additionDate;
    private Integer constructionCompanyID;
    private Integer agencyID;
    private Integer residentialComplexId;
    private Long UserId;
}
