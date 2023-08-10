package com.sparta.demo.repository;

import com.sparta.demo.entity.Board;
import com.sparta.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {
    List<Board> findByUser(User user);
}
