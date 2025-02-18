package com.matrix.buildingapp.repository;



import com.matrix.buildingapp.model.entity.ResidentialComplex;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResidentialComplexRepository extends JpaRepository<ResidentialComplex,Integer> {
ResidentialComplex findByName(String name);
}
