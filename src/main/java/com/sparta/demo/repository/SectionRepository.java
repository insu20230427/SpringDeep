package com.sparta.demo.repository;

import com.sparta.demo.entity.Section;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SectionRepository extends JpaRepository<Section, Long> {
    List<Section> findByBoardId(Long boardId);
}
