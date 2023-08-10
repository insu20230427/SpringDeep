package com.sparta.demo.controller;

import com.sparta.demo.dto.response.ApiResponseDto;
import com.sparta.demo.dto.reuqest.LoginRequestDto;
import com.sparta.demo.dto.reuqest.SignupRequestDto;
import com.sparta.demo.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class UserController {

    private final UserService userService;


        //회원가입
        @PostMapping("/signup")
        public ResponseEntity<ApiResponseDto>signup(@RequestBody SignupRequestDto requestDto){
            userService.signup(requestDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponseDto("회원가입 완료", HttpStatus.CREATED.value()));
        }



        //로그인
        @PostMapping("/login")
        public ResponseEntity<ApiResponseDto>login(@RequestBody LoginRequestDto requestDto, HttpServletResponse response){
            userService.login(requestDto,response);
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponseDto("로그인성공", HttpStatus.OK.value()));
        }
    }


