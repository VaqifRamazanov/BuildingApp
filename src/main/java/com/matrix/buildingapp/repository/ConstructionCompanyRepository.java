package com.matrix.buildingapp.repository;



import com.matrix.buildingapp.model.entity.ConstructionCompany;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConstructionCompanyRepository extends JpaRepository<ConstructionCompany, Integer> {
}
