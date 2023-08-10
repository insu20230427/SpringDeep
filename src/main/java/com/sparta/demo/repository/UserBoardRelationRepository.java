package com.sparta.demo.repository;

import com.sparta.demo.entity.UserBoardRelation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserBoardRelationRepository extends JpaRepository<UserBoardRelation, Long> {
    UserBoardRelation findByUserIdAndBoardId(Long userId, Long boardId);
}
