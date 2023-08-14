package com.sparta.demo.service;

import com.sparta.demo.dto.reuqest.SectionRequestDto;
import com.sparta.demo.dto.response.SectionResponseDto;
import com.sparta.demo.entity.Board;
import com.sparta.demo.entity.Section;
import com.sparta.demo.repository.BoardRepository;
import com.sparta.demo.repository.SectionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SectionService {

    private final SectionRepository sectionRepository;
    private final BoardRepository boardRepository;

    //섹션 생성
    public void createSection(SectionRequestDto sectionRequestDto) {
        Long boardId = sectionRequestDto.getBoardId();
        String columnName = sectionRequestDto.getColumnName();

        Board board = boardRepository.findById(boardId).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 보드"));
        Section section = new Section(columnName, board);

        sectionRepository.save(section);
    }

    //해당 보드 전체 섹션 조회
    public List<SectionResponseDto> showSection(Long boardId) {
//        List<SectionResponseDto> sectionList = sectionRepository.findByBoardId(boardId).stream().map(SectionResponseDto::new).toList(); //cardList 받아오는지 확인
        List<Section> sectionList = sectionRepository.findByBoardId(boardId);
        List<SectionResponseDto> sectionResponseDtoList = new ArrayList<>();
        for (int i = sectionList.size() - 1; i >= 0; i--) {
            sectionResponseDtoList.add(new SectionResponseDto(sectionList.get(i)));
        }
        return sectionResponseDtoList;
    }

    //섹션 수정
    @Transactional
    public void updateSection(Long section_id, SectionRequestDto sectionRequestDto) {
        Section section = sectionRepository.findById(section_id).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 섹션"));
        String columnName = sectionRequestDto.getColumnName();
        section.updateSection(columnName);
    }

    //섹션 삭제
    public void deleteSection(Long sectionId) {
        Section section = findSection(sectionId);
        sectionRepository.delete(section);
    }

    //특정 섹션 조회
    private Section findSection(Long id) {
        return sectionRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 섹션"));
    }
}
