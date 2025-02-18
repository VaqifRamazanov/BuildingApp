package com.matrix.buildingapp.repository;

import com.matrix.buildingapp.model.entity.Favorite;
import com.matrix.buildingapp.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.beans.JavaBean;

public interface FavoriteRepository extends JpaRepository<Favorite,Long> {
    Favorite findByUser(User user);
}
