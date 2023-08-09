package com.sparta.demo.dto;

import com.sparta.demo.entity.Section;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class SectionResponseDto {
    private Long sectionId;
    private String sectionName;
    private List<CardResponseDto> cards;

    public SectionResponseDto(Section section){
        this.sectionId = section.getId();
        this.sectionName = section.getColumnName();
        this.cards = section.getCardList().stream().map(CardResponseDto::new).toList();
    }

}
