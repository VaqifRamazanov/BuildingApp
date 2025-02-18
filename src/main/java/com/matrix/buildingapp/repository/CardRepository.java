package com.matrix.buildingapp.repository;

import com.matrix.buildingapp.model.dto.responseDto.CardResponseDto;
import com.matrix.buildingapp.model.entity.Announcement;
import com.matrix.buildingapp.model.entity.Card;
import com.matrix.buildingapp.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CardRepository extends JpaRepository<Card,Long> {
    Card findByBankAccount(String bankAccount);

}
