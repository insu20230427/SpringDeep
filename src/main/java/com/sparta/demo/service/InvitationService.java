package com.sparta.demo.service;


import com.sparta.demo.dto.response.ApiResponseDto;
import com.sparta.demo.dto.response.BoardResponseDto;
import com.sparta.demo.dto.response.InvitationResponseDto;
import com.sparta.demo.entity.Board;
import com.sparta.demo.entity.User;
import com.sparta.demo.entity.UserBoardRelation;
import com.sparta.demo.repository.BoardRepository;
import com.sparta.demo.repository.UserBoardRelationRepository;
import com.sparta.demo.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class InvitationService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BoardRepository boardRepository;
    @Autowired
    private UserBoardRelationRepository userBoardRelationRepository;

    public ResponseEntity<ApiResponseDto> inviteUserToBoard(Long userId, Long boardId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않은 유저입니다."));
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않은 보드입니다."));

        // 해당되는 userId, BoardId가 존재하지 않거나, 이미 초대된 사용자임을 검증하는 로직
        UserBoardRelation existingRelation = userBoardRelationRepository.findByUserIdAndBoardId(userId, boardId);
        if (existingRelation != null && "INVITED".equals(existingRelation.getInvitationStatus())) {
            return ResponseEntity.status(400).body(new ApiResponseDto(HttpStatus.BAD_REQUEST.value(), "이미 초대된 사용자입니다."));
        }

        // 초대 로직 구현
        UserBoardRelation relation = new UserBoardRelation();
        relation.setUser(user);
        relation.setBoard(board);
        relation.setInvitationStatus("INVITED"); // 초대 상태 : INVITED 설정

        userBoardRelationRepository.save(relation);

        return ResponseEntity.ok(new ApiResponseDto(HttpStatus.OK.value(), "보드 초대 성공"));

    }

    public ResponseEntity<ApiResponseDto> acceptInvitation(Long relationId) {
        UserBoardRelation relation = userBoardRelationRepository.findById(relationId)
                .orElseThrow(() -> new IllegalArgumentException("초대장을 찾을 수 없습니다."));

        if (!"INVITED".equals(relation.getInvitationStatus())) {
            return ResponseEntity.status(400).body(new ApiResponseDto(HttpStatus.BAD_REQUEST.value(), "이미 초대를 수락한 사용자입니다."));
        }

        relation.setInvitationStatus("ACCEPTED");
        userBoardRelationRepository.save(relation);

        return ResponseEntity.ok(new ApiResponseDto(HttpStatus.OK.value(), "초대 수락 성공", new BoardResponseDto(relation.getBoard())));
    }

    public List<InvitationResponseDto> getAllInvite(User user) {
        List<UserBoardRelation> invitedLIst = userBoardRelationRepository.findByUserIdAndInvitationStatus(user.getId(), "INVITED");
        List<InvitationResponseDto> invitationResponseDtoList = new ArrayList<>();

        for (int i = 0; i < invitedLIst.size(); i++) {
            invitationResponseDtoList.add(new InvitationResponseDto(invitedLIst.get(i)));
        }

        return invitationResponseDtoList;
    }
}