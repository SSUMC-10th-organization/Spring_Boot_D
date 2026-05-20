package com.example.umc_week4.domain.member.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

public class MemberReqDTO {

    // 회원가입
    public record Signup(
            @NotBlank
            String gender,

            @NotNull
            @Past(message = "생년월일은 과거 날짜여야 합니다.")
            LocalDate birth,

            @NotBlank
            String address,

            @JsonProperty("detail_address")
            String detailAddress,

            @NotBlank
            @JsonProperty("phone_number")
            String phoneNumber,

            @NotBlank
            String username,

            @NotBlank
            // 조건 추가 가능
            //@Size(min = 8, message = "비밀번호는 최소 8자 이상")
            String password,

            @NotBlank
            @Email
            String email,

            @NotEmpty
            @JsonProperty("agreed_terms")
            List<Long> agreedTerms,

            @NotEmpty
            @JsonProperty("prefer_food_types")
            List<Integer> preferFoodTypes
    ) {
    }

    // Request body
    public record RequestBody(
            String stringTest,
            Long longTest
    ) {
    }

    // 마이페이지
    public record GetInfo(
            Long id
    ) {
    }

    // public static class
    @Getter
    public static class RequestBodyClass {
        private String stringTest;
        private Long longTest;
    }
}