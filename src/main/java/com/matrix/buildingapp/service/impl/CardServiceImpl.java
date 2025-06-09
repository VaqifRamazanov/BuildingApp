package com.matrix.buildingapp.service.impl;

import com.matrix.buildingapp.exception.AlreadyExistException;
import com.matrix.buildingapp.exception.NotFoundException;
import com.matrix.buildingapp.mapper.CardMapper;
import com.matrix.buildingapp.model.dto.requestDto.CardRequestDto;
import com.matrix.buildingapp.model.dto.responseDto.CardResponseDto;
import com.matrix.buildingapp.model.entity.Card;
import com.matrix.buildingapp.model.entity.User;
import com.matrix.buildingapp.repository.CardRepository;
import com.matrix.buildingapp.repository.UserRepository;
import com.matrix.buildingapp.service.CardService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class CardServiceImpl implements CardService {
    private final CardRepository cardRepository;
    private final CardMapper cardMapper;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public CardResponseDto add(Long id, CardRequestDto requestDto) {
        log.info("Adding card started for User ID: {}, Bank Account: {}", id, requestDto.getBankAccount());

        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found"));

        if (cardRepository.findByBankAccount(requestDto.getBankAccount()) != null) {
            throw new AlreadyExistException("This card already been used");
        }

        Card card = cardMapper.mapToEntity(requestDto);
        card.setCreatedAt(LocalDateTime.now());

        // *** KEY CHANGE: Manage the relationship correctly ***
        card.setUser(user); // Set the user for the card
        user.setCard(card); // Set the card for the user (if needed)

        // *** PERSIST the Card ONLY.  The User will be updated due to the relationship. ***
        cardRepository.save(card);


        CardResponseDto cardResponseDto = cardMapper.mapToResponse(card);
        log.info("Adding of card is finished for Bank Account: {}", requestDto.getBankAccount());
        return cardResponseDto;
    }

    @Override
    public CardResponseDto getById(Long id) {
        log.info("getCartById.started for Cart ID: {}", id);

        Card cart = cardRepository.findById(id).orElseThrow(() -> {
            log.error("getCartById.error: Cart not found with ID: {}", id);
            return new NotFoundException("Cart not found with " + id);
        });

        log.info("getCartById.success for Cart ID: {}", id);
        return cardMapper.mapToResponse(cart);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        log.info("deleteCart.started for Cart ID: {}", id);


//        Card card = cardRepository.findById(id).orElseThrow(() -> new NotFoundException("Cart not found"));
//        User user = userRepository.findById(card.getUser().getId()).orElseThrow(() -> new NotFoundException("user not found"));
//        // User user=cart.getUser();
//        cardRepository.deleteById(id);
//        userRepository.save(user);
//        log.info("ActionLog.deleteCart.finished for Cart ID: {}", id);

        log.info("deleteCart.started for Cart ID: {}", id);

        Card card = cardRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Cart not found"));

        // *** KEY CHANGE: Break the bidirectional relationship ***
        User user = card.getUser();
        if (user != null) {  // Handle cases where a card might not have a user
            user.setCard(null); // Remove the card from the user's side
            userRepository.save(user); // Save the user to persist the change
        }

        cardRepository.delete(card); // Now delete the card
        log.info("ActionLog.deleteCart.finished for Cart ID: {}", id);
    }

}
