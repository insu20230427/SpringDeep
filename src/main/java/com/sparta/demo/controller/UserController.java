package com.sparta.demo.controller;

import com.sparta.demo.dto.LoginRequestDto;
import com.sparta.demo.dto.SignupRequestDto;
import com.sparta.demo.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/auth")
public class UserController {

    private final UserService userService;

    //회원가입
    @PostMapping("/signup")
    public String signup(@ModelAttribute SignupRequestDto requestDto){
        userService.signup(requestDto);
        return "index";
    }

    //로그인
    @PostMapping("/login")
    public String login(@ModelAttribute LoginRequestDto requestDto, HttpServletResponse response){
        userService.login(requestDto,response);
        return "index";
    }
}