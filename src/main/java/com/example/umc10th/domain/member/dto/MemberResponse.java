package com.example.umc10th.domain.member.dto;

public class MemberResponse {

    // 마이 페이지 조회용 응답 DTO
    public record MyPageDTO(
            Long memberId,
            String name,
            String nickname,
            String email,
            String phoneNumber,
            Long point
    ) {}
}
