package com.sparta.demo.controller;

import com.sparta.demo.dto.reuqest.LoginRequestDto;
import com.sparta.demo.dto.reuqest.SignupRequestDto;
import com.sparta.demo.security.UserDetailsImpl;
import com.sparta.demo.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/auth")
public class UserController {

    private final UserService userService;


    //회원가입
    @PostMapping("/signup")
    public String signup(@ModelAttribute SignupRequestDto requestDto){
        userService.signup(requestDto);
        return "redirect:/";
    }



    //로그인
    @PostMapping("/login")
    public String login(@ModelAttribute LoginRequestDto requestDto, HttpServletResponse response){
        userService.login(requestDto,response);
        return "redirect:/";
    }


    //유저 이름 가져오는 컨트롤러
    @ResponseBody
    @GetMapping("/get-user")
    public String getUserName(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return userDetails.getUser().getUsername();
    }
}