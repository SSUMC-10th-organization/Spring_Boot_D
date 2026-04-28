package com.example.umc10th.domain.member.dto;

import lombok.Builder;

public class MemberReqDTO {

    @Builder
    public record JoinDTO(
            String nickname,
            String gender,
            String login_type,
            String email,
            String phone_number
    ) {}

}
