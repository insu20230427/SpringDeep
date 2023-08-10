package com.sparta.demo.controller;

import com.sparta.demo.dto.reuqest.CommentRequestDto;
import com.sparta.demo.dto.response.CommentResponseDto;
import com.sparta.demo.entity.User;
import com.sparta.demo.security.UserDetailsImpl;
import com.sparta.demo.service.CommentService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/card")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    // 댓글 생성
    @PostMapping("/comment")
    public CommentResponseDto createComment(@RequestBody CommentRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        User user = userDetails.getUser();
        return commentService.createComment(requestDto, user);
    }

    // 댓글 수정
    @PutMapping("/comment/{id}")
    public CommentResponseDto updateComment(@PathVariable Long id, @RequestBody CommentRequestDto requestDto , @AuthenticationPrincipal UserDetailsImpl userDetails) {
        User user = userDetails.getUser();
        return commentService.updateComment(id, requestDto , user);
    }

    //댓글 삭제
    @DeleteMapping("/comment/{id}")
    public String deleteComment(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        User user = userDetails.getUser();
        commentService.deleteComment(id, user);
        return "삭제 완료";
    }
}
