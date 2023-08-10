package com.sparta.demo.service;


import com.sparta.demo.entity.Board;
import com.sparta.demo.entity.User;
import com.sparta.demo.entity.UserBoardRelation;
import com.sparta.demo.repository.BoardRepository;
import com.sparta.demo.repository.UserBoardRelationRepository;
import com.sparta.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InvitationService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BoardRepository boardRepository;
    @Autowired
    private UserBoardRelationRepository userBoardRelationRepository;

    public void inviteUserToBoard(Long userId, Long boardId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않은 유저입니다."));
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않은 보드입니다."));

        // 이미 초대된 사용자인지 확인 (초대 상태도 확인)
        UserBoardRelation existingRelation = userBoardRelationRepository.findByUserIdAndBoardId(userId, boardId);
        if (existingRelation != null && "INVITED".equals(existingRelation.getInvitationStatus())) {
            throw new IllegalArgumentException("이미 초대된 사용자입니다.");
        }

        // 초대 로직 구현
        UserBoardRelation relation = new UserBoardRelation();
        relation.setUser(user);
        relation.setBoard(board);
        relation.setInvitationStatus("INVITED"); // 초대 상태 설정

        userBoardRelationRepository.save(relation);

    }

    public Board acceptInvitation(Long relationId) {
        UserBoardRelation relation = userBoardRelationRepository.findById(relationId)
                .orElseThrow(() -> new IllegalArgumentException("초대장을 찾을 수 없습니다.."));

        if (!"INVITED".equals(relation.getInvitationStatus())) {
            throw new IllegalStateException("초대장이 올바른 상태가 아닙니다.");
        }

        relation.setInvitationStatus("ACCEPTED");
        userBoardRelationRepository.save(relation);

        return relation.getBoard(); // 초대를 수락한 보드를 반환
    }
}