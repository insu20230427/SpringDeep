package com.sparta.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sparta.demo.dto.reuqest.BoardRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "board")
@RequiredArgsConstructor
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="board_name" , nullable = false)
    private String boardname;
    @Column(nullable = false)
    private String boardDescription;
    @Column(nullable = false)
    private String boardColor;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "board", cascade = CascadeType.PERSIST, orphanRemoval = true)
    List<Section> sectionList = new ArrayList<>();

    public Board(BoardRequestDto requestDto, User user) {
        this.boardname = requestDto.getBoardname();
        this.boardDescription = requestDto.getBoardDescription();
        this.boardColor = requestDto.getBoardColor();
        this.user = user;
    }
}
