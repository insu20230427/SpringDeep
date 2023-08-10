package com.sparta.demo.service;

import com.sparta.demo.dto.response.ApiResponseDto;
import com.sparta.demo.dto.response.BoardResponseDto;
import com.sparta.demo.dto.reuqest.BoardRequestDto;
import com.sparta.demo.entity.Board;
import com.sparta.demo.entity.User;
import com.sparta.demo.repository.BoardRepository;
import com.sparta.demo.repository.UserBoardRelationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;


@Service
@RequiredArgsConstructor
@Slf4j(topic = "Board Service")
public class BoardService {

    private final BoardRepository boardRepository;
    private final UserBoardRelationRepository userBoardRelationRepository;

    public BoardResponseDto createBoard(BoardRequestDto requestDto, User user) {
        Board board = new Board(requestDto, user);
        Board savedBoard = boardRepository.save(board);
        return new BoardResponseDto(savedBoard);
    }

//    public List<BoardResponseDto> getBoards() {
//        return boardRepository.findAll().stream().map(BoardResponseDto::new).toList();
//    }

//    public ResponseEntity<ApiResponseDto> getInvitedBoards(User user) {
//        List<Board> boards = userBoardRelationRepository.findInvitedBoardsByUser(user);
//        List<BoardResponseDto> invitedBoards = boards.stream().map(BoardResponseDto::new).toList();
//        return ResponseEntity.status(200).body(new ApiResponseDto(HttpStatus.OK.value(),"초대받은 보드 조회 성공", invitedBoards));
//    }


    public ResponseEntity<ApiResponseDto> getAllUserBoards(User user) {

        // 사용자가 생성한 보드 조회
        List<Board> createdBoards = boardRepository.findByUser(user);

        // 사용자가 초대받은 보드 조회
        List<Board> invitedBoards = userBoardRelationRepository.findInvitedBoardsByUser(user);

        // 중복방지(Set)
        Set<Board> uniqueBoards = new LinkedHashSet<>(createdBoards);

        // 중복 검사 후 Set에 추가 로직
        for (Board board : invitedBoards) {
            if (!uniqueBoards.contains(board)) {
                uniqueBoards.add(board);
            }
        }

        Iterator<Board> iter = uniqueBoards.iterator();

        // Set을 리스트로 변환하여 반환합니다.
        List<Board> allBoards = new ArrayList<>(uniqueBoards);

        // type : Board -> BoardResponseDto
        List<BoardResponseDto> allBoardDtos = allBoards.stream().map(BoardResponseDto::new).toList();
        return ResponseEntity.status(200).body(new ApiResponseDto(HttpStatus.OK.value(), "사용자 보드 조회 성공", allBoardDtos));
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
            log.error("보드가 존재하지 않습니다.");
            return ResponseEntity.status(400).body(new ApiResponseDto(HttpStatus.BAD_REQUEST.value(), "보드 수정 실패"));
        }

        boardRepository.delete(board.get());

        return ResponseEntity.status(200).body(new ApiResponseDto("보드 삭제 성공", HttpStatus.OK.value()));
    }

    public ResponseEntity<ApiResponseDto> updateBoard(Long id, BoardRequestDto requestDto, User user) {
        Board board = findBoard(id);

        if (!Objects.equals(board.getUser().getUsername(), user.getUsername())) {
            log.error("보드가 존재하지 않거나, 수정 권한이 없습니다.");
            return ResponseEntity.status(400).body(new ApiResponseDto(HttpStatus.BAD_REQUEST.value(), "보드 수정 실패"));
        }

        board.setBoardname(requestDto.getBoardname());
        board.setBoardDescription(requestDto.getBoardDescription());
        board.setBoardColor(requestDto.getBoardColor());

        return ResponseEntity.status(200).body(new ApiResponseDto(HttpStatus.OK.value(), "보드 수정 성공", new BoardResponseDto(boardRepository.save(board))));
    }
}
