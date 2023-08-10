package com.sparta.demo.dto.reuqest;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CardRequestDto {
    private Long sectionId;
    private String title;
    private String content;
    private String color;
}
