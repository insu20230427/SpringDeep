package com.sparta.demo.controller;

import com.sparta.demo.dto.response.ApiResponseDto;
import com.sparta.demo.dto.reuqest.SectionRequestDto;
import com.sparta.demo.dto.response.SectionResponseDto;
import com.sparta.demo.service.SectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class SectionController {

    private final SectionService sectionService;

    @PostMapping("/column")
    public ResponseEntity<ApiResponseDto> createSection(@RequestBody SectionRequestDto sectionRequestDto){
        sectionService.createSection(sectionRequestDto);
        return ResponseEntity.ok().body(new ApiResponseDto("섹션 생성 완료", HttpStatus.OK.value()));
    }

    @GetMapping("/column/{board_id}")
    public ResponseEntity<ApiResponseDto> showSection(@PathVariable(value = "board_id") Long board_id){
        List<SectionResponseDto> sectionList = sectionService.showSection(board_id);
        return ResponseEntity.ok().body(new ApiResponseDto(HttpStatus.OK.value(),"섹션조회.", sectionList));
    }

    @PutMapping("/column/{section_id}")
    public ResponseEntity<ApiResponseDto> updateSection(@PathVariable(value = "section_id") Long section_id, @RequestBody SectionRequestDto sectionRequestDto){
        sectionService.updateSection(section_id, sectionRequestDto);
        return ResponseEntity.ok().body(new ApiResponseDto("섹션 수정 완료" ,HttpStatus.OK.value()));
    }

    @DeleteMapping("/column/{section_id}")
    public ResponseEntity<ApiResponseDto> deleteSection(@PathVariable(value = "section_id") Long section_id){
        sectionService.deleteSection(section_id);
        return ResponseEntity.ok().body(new ApiResponseDto("섹션 삭제 완료" , HttpStatus.OK.value()));
    }
}
