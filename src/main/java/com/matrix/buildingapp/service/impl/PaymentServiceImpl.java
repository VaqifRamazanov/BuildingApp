package com.matrix.buildingapp.service.impl;

import com.matrix.buildingapp.exception.InvalidBalanceException;
import com.matrix.buildingapp.exception.NotFoundException;
import com.matrix.buildingapp.model.entity.Announcement;
import com.matrix.buildingapp.model.entity.Card;
import com.matrix.buildingapp.model.entity.User;
import com.matrix.buildingapp.repository.CardRepository;
import com.matrix.buildingapp.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentServiceImpl {

    public void paymentMethod(User user, double amount) {
        Integer countAnnouncement = user.getAnnouncement().size();
        if (countAnnouncement >= 2) {
            if (user.getCard().getBalance()>=amount) {
                user.getCard().setBalance(user.getCard().getBalance() - amount);
            }else throw new InvalidBalanceException("balanced not found");
            System.out.println("Successfully charged user " + user.getUsername() + " for " + amount + " AZN using card " + user.getCard().getBankAccount());
        }
    }
}
