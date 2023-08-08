package com.sparta.demo.service;

import com.sparta.demo.dto.CardRequestDto;
import com.sparta.demo.dto.CardResponseDto;
import com.sparta.demo.entity.Card;
import com.sparta.demo.repository.CardRepository;
import org.springframework.stereotype.Service;

@Service
public class CardService {

    private final CardRepository cardRepository;

    public CardService(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    public CardResponseDto createCard(CardRequestDto requestDto) {
        Card card = new Card(requestDto);
        cardRepository.save(card);
        return new CardResponseDto(card);
    }
}
