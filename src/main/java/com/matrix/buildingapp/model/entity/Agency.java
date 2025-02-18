package com.matrix.buildingapp.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Data
@Table(name="agencies")
public class Agency {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @OneToMany(mappedBy = "agency",cascade = CascadeType.ALL,fetch = LAZY)
    private List<Announcement> announcement;

    @OneToMany(mappedBy = "agency",cascade = CascadeType.ALL,fetch = LAZY)
    private List<User> user;
}
