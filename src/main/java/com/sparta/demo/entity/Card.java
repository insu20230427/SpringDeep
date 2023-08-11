package com.sparta.demo.entity;

import com.sparta.demo.dto.reuqest.CardRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "card")
@NoArgsConstructor
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String content;
    @Column(nullable = false)
    private String color;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "section_id")
    private Section section;

    @OneToMany(mappedBy = "card", cascade = CascadeType.PERSIST, orphanRemoval = true)
    List<Comment> commentList = new ArrayList<>();


    public Card(CardRequestDto requestDto, User user, Section section) {
        username = user.getUsername();
        title = requestDto.getTitle();
        content = requestDto.getContent();
        color = requestDto.getColor();
        this.user = user;
        this.section = section;
    }

    public void update(CardRequestDto requestDto) {
        title = requestDto.getTitle();
        content = requestDto.getContent();
        color = requestDto.getColor();
    }
}
