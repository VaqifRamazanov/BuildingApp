package com.matrix.buildingapp.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "favorites")
public class Favorite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    private User user;

    @ManyToMany
    @JoinTable(name="favorite_announcement"
            ,joinColumns = @JoinColumn(name = "favorite_id")
            ,inverseJoinColumns = @JoinColumn(name = "announcement_id"))
    private List<Announcement> announcement;
}
