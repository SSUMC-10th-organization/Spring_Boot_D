package com.example.umc10thmission4.domain.member.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class MemberResDTO {

    @Builder
    public record GetInfo(
            String name,
            String profileUrl,
            String email,
            String phoneNumber,
            Integer point
    ){}

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class JoinResultDTO {
        Long memberId;
        LocalDateTime createdAt;
        String email;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class LoginResultDTO {
        Long memberId;
        String accessToken;
        LocalDateTime loginAt;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class GetInfoResultDTO {
        private String email;
        private String name;
        private Integer point;
        private String phoneNumber;
        private String profileUrl;
    }

    @Builder
    @Getter
    public static class Login {
        private String accessToken;
    }
}
