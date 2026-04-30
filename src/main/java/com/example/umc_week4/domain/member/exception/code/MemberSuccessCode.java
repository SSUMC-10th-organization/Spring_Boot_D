package com.example.umc_week4.domain.member.exception.code;

import com.example.umc_week4.global.apiPayload.code.BaseSuccessCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum MemberSuccessCode implements BaseSuccessCode {

    OK(
            HttpStatus.OK,
            "MEMBER200_1",
            "성공적으로 유저를 조회했습니다."
    ),

    SIGNUP_SUCCESS(
            HttpStatus.OK,
            "AUTH200_1",
            "회원가입이 완료되었습니다."
    ),

    HOME_SUCCESS(
            HttpStatus.OK,
            "HOME200_1",
            "홈 화면 정보를 조회했습니다."
    );

    private final HttpStatus status;
    private final String code;
    private final String message;
}