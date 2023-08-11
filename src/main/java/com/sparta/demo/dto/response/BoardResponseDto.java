package com.sparta.demo.dto.response;

import com.sparta.demo.entity.Board;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@RequiredArgsConstructor
public class BoardResponseDto {
    private Long id;
    private String boardname;
    private String boardDescription;
    private String boardColor;
//    private List<SectionResponseDto> sectionResponseDtoList;

    public BoardResponseDto(Board board) {
        this.id = board.getId();
        this.boardname = board.getBoardname();
        this.boardDescription = board.getBoardDescription();
        this.boardColor = board.getBoardColor();
//        this.sectionResponseDtoList = board.getSectionList().stream().map(SectionResponseDto::new).collect(Collectors.toList());
    }
}
