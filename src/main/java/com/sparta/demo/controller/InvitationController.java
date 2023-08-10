package com.sparta.demo.controller;

import com.sparta.demo.dto.BoardResponseDto;
import com.sparta.demo.entity.Board;
import com.sparta.demo.service.InvitationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/invitation")
public class InvitationController {
    @Autowired
    private InvitationService invitationService;


    @PostMapping("/invite")
    public ResponseEntity<String> inviteUserToBoard(@RequestParam Long userId, @RequestParam Long boardId) {
        try {
            invitationService.inviteUserToBoard(userId, boardId);
            return ResponseEntity.ok("성공적으로 초대가 되었습니다.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/accept")
    public ResponseEntity<BoardResponseDto> acceptInvitation(@RequestParam Long relationId) {
        Board board = invitationService.acceptInvitation(relationId);
        BoardResponseDto responseDto = new BoardResponseDto(board); // BoardResponseDto는 보드 정보를 담는 DTO
        return ResponseEntity.ok(responseDto);
    }
}

