package com.example.umc10jinho.domain.member.dto;

public class MemberResDTO {

    public record SignUpResponse(
            Long userId,
            String name,
            String email
    ) {
    }

    public record LoginResponse(
            Long userId,
            String accessToken
    ) {
    }

    public record MyInfoResponse(
            Long userId,
            String name,
            String gender,
            String email,
            String address,
            Integer point
    ) {
    }
}
