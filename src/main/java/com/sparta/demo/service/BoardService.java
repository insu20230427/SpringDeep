package com.sparta.demo.service;

import com.sparta.demo.dto.ApiResponseDto;
import com.sparta.demo.dto.BoardRequestDto;
import com.sparta.demo.dto.BoardResponseDto;
import com.sparta.demo.entity.Board;
import com.sparta.demo.entity.User;
import com.sparta.demo.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Service
@RequiredArgsConstructor
@Slf4j(topic = "Board Service")
public class BoardService {

    private final BoardRepository boardRepository;

    public BoardResponseDto createBoard(BoardRequestDto requestDto, User user) {
        Board board = new Board(requestDto, user);
        Board savedBoard = boardRepository.save(board);
        return new BoardResponseDto(savedBoard);
    }

    public List<BoardResponseDto> getBoards() {
        return boardRepository.findAll().stream().map(BoardResponseDto::new).toList();
    }

    public BoardResponseDto lookupBoard(Long id) {
        Board board = findBoard(id);
        return new BoardResponseDto(board);
    }

    private Board findBoard(Long id) {
        return boardRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("선택한 보드는 존재하지 않습니다."));
    }

    @Transactional
    public ResponseEntity<ApiResponseDto> deleteBoard(Long id, User user) {
        Optional<Board> board = boardRepository.findById(id);

        if (!board.isPresent() || !Objects.equals(board.get().getUser().getUsername(), user.getUsername())) {
            log.error("게시글이 존재하지 않거나 수정 권한이 없습니다.");
            return ResponseEntity.status(400).body(new ApiResponseDto("보드 삭제 실패", HttpStatus.BAD_REQUEST.value()));
        }

        boardRepository.delete(board.get());

        return ResponseEntity.status(200).body(new ApiResponseDto("보드 삭제 성공", HttpStatus.OK.value()));
    }

    public BoardResponseDto updateBoard(Long id, BoardRequestDto requestDto, User user) {
        Board board = findBoard(id);

        if (!Objects.equals(board.getUser().getUsername(), user.getUsername())) {
            throw new IllegalArgumentException("수정 권한이 없습니다.");
        }

        board.setBoardname(requestDto.getBoardname());
        board.setBoardDescription(requestDto.getBoardDescription());
        board.setBoardColor(requestDto.getBoardColor());

        return new BoardResponseDto(boardRepository.save(board));
    }
}