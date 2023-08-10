package com.sparta.demo.dto.response;

import com.sparta.demo.entity.Card;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class CardResponseDto {

    private Long id;
    private String username;
    private String title;
    private String content;
    private String color;
    // CommentResponseDtoList도 넣어야됨
    private List<CommentResponseDto> commentResponseDtoList = new ArrayList<>();

    public CardResponseDto(Card card) {
        id = card.getId();
        username = card.getUsername();
        title = card.getTitle();
        content = card.getContent();
        color = card.getColor();

        // 댓글 리스트 가져오기
        for (int i = 0; i < card.getCommentList().size(); i++) {
            commentResponseDtoList.add(new CommentResponseDto(card.getCommentList().get(i)));
        }
    }
}