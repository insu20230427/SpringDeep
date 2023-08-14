package com.sparta.demo.dto.reuqest;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InvitationRequestDto {
    private String username; // 초대받는 사용자의 ID
    private Long boardId; // 초대하는 보드의 ID
}

