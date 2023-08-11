package com.sparta.demo.repository;

import com.sparta.demo.entity.Board;
import com.sparta.demo.entity.User;
import com.sparta.demo.entity.UserBoardRelation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserBoardRelationRepository extends JpaRepository<UserBoardRelation, Long> {
    UserBoardRelation findByUserIdAndBoardId(Long userId, Long boardId);
    // 반환 형식을 List<Board>로 변경
    @Query("SELECT ubr.board FROM UserBoardRelation ubr WHERE ubr.user = :user AND ubr.invitationStatus = 'ACCEPTED'")
    List<Board> findInvitedBoardsByUser(@Param("user") User user);
}
