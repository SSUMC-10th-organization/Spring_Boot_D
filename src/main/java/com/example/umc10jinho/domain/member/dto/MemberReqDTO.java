package com.example.umc10jinho.domain.member.dto;

import java.util.List;

public class MemberReqDTO {

    public record SignUpRequest(
            String name,
            String gender,
            String born,
            String email,
            String address,
            List<Long> preferredFoodIds
    ) {
    }

    public record LoginRequest(
            String email,
            String password
    ) {
    }
}
