package com.sparta.demo.dto;

import com.sparta.demo.entity.Card;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CardResponseDto {

    private Long id;
    private String username;
    private String title;
    private String content;
    private String color;

    // UserResponseDto도 넣어야됨
    // CommentResponseDtoList도 넣어야됨
    public CardResponseDto(Card card) {
        id = card.getId();
        username = card.getUsername();
        title = card.getTitle();
        content = card.getContent();
        color = card.getColor();
    }
}