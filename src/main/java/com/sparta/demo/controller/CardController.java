package com.sparta.demo.controller;

import com.sparta.demo.dto.CardRequestDto;
import com.sparta.demo.dto.CardResponseDto;
import com.sparta.demo.service.CardService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CardController {

    private final CardService cardService;

    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @PostMapping("/card")
    public CardResponseDto createCard(@RequestBody CardRequestDto requestDto) {
        return cardService.createCard(requestDto);
    }

}
