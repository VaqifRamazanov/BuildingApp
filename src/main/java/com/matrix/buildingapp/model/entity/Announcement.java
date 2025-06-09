package com.matrix.buildingapp.model.entity;

import com.matrix.buildingapp.enums.AnnouncementForm;
import com.matrix.buildingapp.enums.AnnouncementType;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Data
@Table(name = "announcements")
public class Announcement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id ;
    private String area;
    @Enumerated(EnumType.STRING)
    private AnnouncementType announcementType;
    @Enumerated(EnumType.STRING)
    private AnnouncementForm announcementForm;
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
    @ManyToOne
    @JoinColumn(name = "residential_complex_id")
    private ResidentialComplex residentialComplex;

    @ManyToOne
    @JoinColumn(name = "agency_id")
    private Agency agency;

    @ManyToOne
    @JoinColumn(name="construction_company id")
    private ConstructionCompany constructionCompany;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


}
