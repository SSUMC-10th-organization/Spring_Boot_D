package com.example.umc10thmission4.domain.common.auth.dto;

public class AuthReqDTO {

    public record LoginDTO(
            String email,
            String password
    ) {}
}
