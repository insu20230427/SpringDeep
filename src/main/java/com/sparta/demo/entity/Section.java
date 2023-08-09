package com.sparta.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "section")
@NoArgsConstructor
public class Section {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String columnName;

    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;

    @OneToMany(mappedBy = "section", cascade = CascadeType.PERSIST, orphanRemoval = true)
    List<Card> cardList = new ArrayList<>();

    public Section(String columnName, Board board) {
        this.columnName = columnName;
        this.board = board;
    }

    public void updateSection(String columnName){
        this.columnName = columnName;
    }
}
