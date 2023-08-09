package com.sparta.demo.dto;

import com.sparta.demo.entity.Comment;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentResponseDto {

    private Long id;
    private String username;
    private String content;

    public CommentResponseDto(Comment comment) {
        id = comment.getId();
        username = comment.getUsername();
        content = comment.getContent();
    }
}
