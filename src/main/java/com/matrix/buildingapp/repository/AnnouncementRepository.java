package com.matrix.buildingapp.repository;




import com.matrix.buildingapp.model.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnnouncementRepository extends JpaRepository<Announcement, Integer> {
    List<Announcement> findByAgency(Agency agency);
    List<Announcement> findByResidentialComplex(ResidentialComplex residentialComplex);
    List<Announcement> findByConstructionCompany(ConstructionCompany constructionCompany);
    List<Announcement> findByUser(User user);

}
