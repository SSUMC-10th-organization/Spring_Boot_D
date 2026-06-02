package com.example.umc10thmission4.domain.common.auth.dto;

import lombok.Builder;
import lombok.Getter;

public class AuthResDTO {

    @Builder
    @Getter
    public static class LoginResult {
        private String accessToken;
    }
}
