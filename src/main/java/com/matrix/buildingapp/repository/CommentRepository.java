package com.matrix.buildingapp.repository;


import com.matrix.buildingapp.model.entity.ConstructionCompany;
import com.matrix.buildingapp.model.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
    List<Comment> findByConstructionCompany(ConstructionCompany constructionCompany);
}
