package com.sparta.demo.controller;

import com.sparta.demo.dto.CommentResponseDto;
import com.sparta.demo.service.CommentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/card")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

//    @PostMapping("/comment")
//    public CommentResponseDto createComment(CommentRequestDto requestDto) {
//
//    }
}
