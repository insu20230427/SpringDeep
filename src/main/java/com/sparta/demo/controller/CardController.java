package com.sparta.demo.controller;

import com.sparta.demo.dto.reuqest.CardRequestDto;
import com.sparta.demo.dto.response.CardResponseDto;
import com.sparta.demo.entity.User;
import com.sparta.demo.security.UserDetailsImpl;
import com.sparta.demo.service.CardService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CardController {

    private final CardService cardService;

    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    // card 생성
    @PostMapping("/card")
    public CardResponseDto createCard(@RequestBody CardRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        User user = userDetails.getUser();
        return cardService.createCard(requestDto, user);
    }

    // card 전체 조회( test용도 )
    @GetMapping("/card")
    public List<CardResponseDto> getCards() {
        return cardService.getCards();
    }

    // card 수정
    @PutMapping("/card/{id}")
    public CardResponseDto updateCard(@PathVariable Long id, @RequestBody CardRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        User user = userDetails.getUser();
        return cardService.updateCard(id, requestDto, user);
    }

    // card 삭제
    @DeleteMapping("/card/{id}")
    public String deleteCard(@PathVariable Long id) {
        cardService.deleteCard(id);
        return "삭제 완료";
    }

}
