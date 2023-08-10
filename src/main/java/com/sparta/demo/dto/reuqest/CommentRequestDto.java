package com.sparta.demo.dto.reuqest;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentRequestDto {

    private Long cardId;
    private String content;
}
