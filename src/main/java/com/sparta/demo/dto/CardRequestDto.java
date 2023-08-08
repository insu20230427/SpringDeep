package com.sparta.demo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CardRequestDto {
    private String username;
    private String title;
    private String content;
    private String color;
}
