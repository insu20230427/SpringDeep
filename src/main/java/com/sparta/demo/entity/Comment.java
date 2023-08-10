package com.sparta.demo.entity;

import com.sparta.demo.dto.reuqest.CommentRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "comment")
@NoArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;
    @Column(nullable = false)
    private String content;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "card_id")
    private Card card;

    public Comment(CommentRequestDto requestDto, User user, Card card) {
        username = user.getUsername();
        content = requestDto.getContent();
        this.user = user;
        this.card = card;
    }

    public void update(CommentRequestDto requestDto) {
        content = requestDto.getContent();
    }
}
