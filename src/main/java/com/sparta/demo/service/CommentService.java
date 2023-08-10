package com.sparta.demo.service;

import com.sparta.demo.dto.reuqest.CommentRequestDto;
import com.sparta.demo.dto.response.CommentResponseDto;
import com.sparta.demo.entity.Card;
import com.sparta.demo.entity.Comment;
import com.sparta.demo.entity.User;
import com.sparta.demo.repository.CardRepository;
import com.sparta.demo.repository.CommentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final CardRepository cardRepository;

    public CommentService(CommentRepository commentRepository, CardRepository cardRepository) {
        this.commentRepository = commentRepository;
        this.cardRepository = cardRepository;
    }

    //댓글 생성
    public CommentResponseDto createComment(CommentRequestDto requestDto, User user) {
        // card entity 조회
        Card card = cardRepository.findById(requestDto.getCardId()).orElseThrow(
                () -> new IllegalArgumentException("해당되는 card를 찾을 수 없습니다.")
        );

        // comment entity 생성, 저장
        Comment comment = new Comment(requestDto, user, card);
        commentRepository.save(comment);

        return new CommentResponseDto(comment);
    }

    //댓글 수정
    @Transactional
    public CommentResponseDto updateComment(Long id, CommentRequestDto requestDto , User user) {
        Comment comment = findCommentById(id);

        if (comment.getUser().getId().equals(user.getId())) {
            comment.update(requestDto);
        } else {
            throw new IllegalArgumentException("본인이 작성하지 않은 댓글은 수정할수 없습니다.");
        }

        return new CommentResponseDto(comment);
    }

    //댓글 삭제
    public void deleteComment(Long id, User user) {
        Comment comment = findCommentById(id);

        if (comment.getUser().getId().equals(user.getId()))  {
            commentRepository.delete(comment);
        } else {
            throw new IllegalArgumentException("본인이 작성하는 않은 댓글은 삭제할수 없습니다.");
        }
    }


    /*----------private method-------------*/
    private Comment findCommentById(Long id) {
        return commentRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당되는 댓글을 찾을수 없습니다.")
        );
    }


}
