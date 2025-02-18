package com.matrix.buildingapp.repository;


import com.matrix.buildingapp.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByEmail(String name);

    Optional<User> findByUsername(String username);

}
