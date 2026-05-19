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

    public record JoinResultDTO(
            String email,
            String name,
            Long memberId // 나중에 클라이언트가 가입 후 바로 해당 ID로 뭔가를 할 수도 있어서 포함하는 게 좋습니다.
    ) {}
}
