package com.matrix.buildingapp.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Data
@Table(name = "residential_complexes")
public class ResidentialComplex {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @OneToMany(mappedBy = "residentialComplex",cascade = CascadeType.ALL,fetch = LAZY)
    private List <Announcement> announcement;

}
