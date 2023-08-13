package com.sparta.demo.controller;

import com.sparta.demo.dto.response.ApiResponseDto;
import com.sparta.demo.dto.response.InvitationResponseDto;
import com.sparta.demo.dto.reuqest.InvitationRequestDto;
import com.sparta.demo.entity.User;
import com.sparta.demo.security.UserDetailsImpl;
import com.sparta.demo.service.InvitationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/invitation")
public class InvitationController {

    private final InvitationService invitationService;

    public InvitationController(InvitationService invitationService) {
        this.invitationService = invitationService;
    }

    @ResponseBody
    @PostMapping("/invite")
    public ResponseEntity<ApiResponseDto> inviteUserToBoard(@RequestBody InvitationRequestDto requestDto) {
        return invitationService.inviteUserToBoard(requestDto);
    }

    @PostMapping("/accept")
    public String acceptInvitation(@RequestParam Long relationId) {
        invitationService.acceptInvitation(relationId);
        return "redirect:/";
    }

    @ResponseBody
    @GetMapping("/invite-list")
    public ResponseEntity<ApiResponseDto> getAllInvite(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        User user = userDetails.getUser();
        List<InvitationResponseDto> invationList = invitationService.getAllInvite(user);
        return ResponseEntity.ok().body(new ApiResponseDto(HttpStatus.OK.value(),"목록조회.", invationList));
    }
}

