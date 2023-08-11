package com.sparta.demo.dto.response;

import com.sparta.demo.entity.UserBoardRelation;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class InvitationResponseDto {
    private Long id;
    private String status;

    public InvitationResponseDto(UserBoardRelation userBoardRelation) {
        this.id = userBoardRelation.getId();
        this.boardName = userBoardRelation.getBoard().getBoardname();
        this.status = userBoardRelation.getInvitationStatus();
    }
}


