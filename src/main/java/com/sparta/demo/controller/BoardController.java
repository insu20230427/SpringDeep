package com.sparta.demo.controller;

import com.sparta.demo.dto.response.ApiResponseDto;
import com.sparta.demo.dto.response.BoardResponseDto;
import com.sparta.demo.dto.reuqest.BoardRequestDto;
import com.sparta.demo.security.UserDetailsImpl;
import com.sparta.demo.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequestMapping("/api")
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    @PostMapping("/Board") // 보드 생성
    public String createBoard(@ModelAttribute BoardRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        boardService.createBoard(requestDto, userDetails.getUser());
        return "redirect:/";
    }

//    @GetMapping("/Board")
//    public List<BoardResponseDto> getBoards() {
//        return boardService.getBoards();
//    }

    @ResponseBody
    @GetMapping("/allBoards") // 초대받은 보드 + 본인이 생성한 보드 전체 조회
    public ResponseEntity<ApiResponseDto> getAllUserBoards(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return boardService.getAllUserBoards(userDetails.getUser());
    }

    @ResponseBody
    @GetMapping("/Board/{id}") // 상세 보드 조회
    public BoardResponseDto lookupBoard(@PathVariable Long id) {
        return boardService.lookupBoard(id);
    }

    @PutMapping("/Board/{id}") // 보드 수정
    public String updateBoard(@PathVariable Long id, @RequestBody BoardRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
         boardService.updateBoard(id, requestDto, userDetails.getUser());
         return "redirect:/";
    }

    @DeleteMapping("/Board/{id}") // 상세 보드 삭제
    public String deleteBoard(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails) {
         boardService.deleteBoard(id, userDetails.getUser());
         return "redirect:/";
    }
}

