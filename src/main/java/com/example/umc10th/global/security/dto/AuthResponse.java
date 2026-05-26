package com.example.umc10th.global.security.dto;

public class AuthResponse {
    // 로그인 성공 시 토큰과 유저 정보를 반환
    public record LoginResultDTO(
            String email,
            String accessToken,
            String tokenType // "Bearer" 고정
    ) {}
}
