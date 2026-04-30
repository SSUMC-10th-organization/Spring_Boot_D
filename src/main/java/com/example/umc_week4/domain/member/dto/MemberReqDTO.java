package com.example.umc_week4.domain.member.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

public class MemberReqDTO {

    // 회원가입
    public record Signup(
            String gender,
            LocalDate birth,
            String address,

            @JsonProperty("detail_address")
            String detailAddress,

            @JsonProperty("phone_number")
            String phoneNumber,

            String username,
            String password,
            String email,

            @JsonProperty("agreed_terms")
            List<Long> agreedTerms,

            @JsonProperty("prefer_food_types")
            List<Integer> preferFoodTypes
    ) {
    }

    // Request body 예시 - 기존 실습 코드 유지
    public record RequestBody(
            String stringTest,
            Long longTest
    ) {
    }

    // 마이페이지 - 기존 실습 코드 유지
    public record GetInfo(
            Long id
    ) {
    }

    // public static class - 기존 실습 코드 유지
    @Getter
    public static class RequestBodyClass {
        private String stringTest;
        private Long longTest;
    }
}