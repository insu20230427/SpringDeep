package com.sparta.demo.controller;

import com.sparta.demo.dto.ApiResponseDto;
import com.sparta.demo.dto.BoardRequestDto;
import com.sparta.demo.dto.BoardResponseDto;
import com.sparta.demo.security.UserDetailsImpl;
import com.sparta.demo.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Slf4j
@Controller
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    @PostMapping("/Board") // 보드 생성
    public BoardResponseDto createBoard(@RequestBody BoardRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return boardService.createBoard(requestDto, userDetails.getUser());
    }

    @GetMapping("/Board") // 전체 보드 조회
    public List<BoardResponseDto> getBoards() {
        return boardService.getBoards();
    }

    @GetMapping("/Board/{id}") // 상세 보드 조회
    public BoardResponseDto lookupBoard(@PathVariable Long id) {
        return boardService.lookupBoard(id);
    }

    @PutMapping("/Board/{id}") // 보드 수정
    public BoardResponseDto updateBoard(@PathVariable Long id, @RequestBody BoardRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return boardService.updateBoard(id, requestDto, userDetails.getUser());
    }

    @DeleteMapping("/Board/{id}") // 상세 게시글 삭제
    public ResponseEntity<ApiResponseDto> deleteBoard(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return boardService.deleteBoard(id, userDetails.getUser());
    }
}

