package com.example.umc10th.domain.member.dto;

import jakarta.validation.constraints.NotNull;

public class MemberRequest {

    public record OngoingMissionDTO (
            @NotNull(message = "memberId는 필수입니다.")
            Long memberId
    ) {}
}
