package com.sparta.demo.controller;

import com.sparta.demo.dto.ApiResponseDto;
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
    public ResponseEntity<ApiResponseDto> inviteUserToBoard(@RequestParam Long userId, @RequestParam Long boardId) {
        return invitationService.inviteUserToBoard(userId, boardId);
    }

    @PostMapping("/accept")
    public ResponseEntity<ApiResponseDto> acceptInvitation(@RequestParam Long relationId) {
        return invitationService.acceptInvitation(relationId);
    }

}

