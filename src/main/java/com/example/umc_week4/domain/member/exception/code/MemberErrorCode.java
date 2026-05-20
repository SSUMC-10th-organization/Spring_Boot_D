package com.example.umc_week4.domain.member.exception.code;

import com.example.umc_week4.global.apiPayload.code.BaseErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum MemberErrorCode implements BaseErrorCode {
    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND,
            "MEMBER404_1",
            "해당 사용자를 찾을 수 없습니다"),

    DUPLICATE_EMAIL(HttpStatus.CONFLICT,
                    "MEMBER409_1",
            "이미 가입된 이메일입니다."),
    INVALID_GENDER(HttpStatus.BAD_REQUEST,
            "MEMBER400_1",
            "올바르지 않은 성별 값입니다."),
    TERM_NOT_FOUND(HttpStatus.NOT_FOUND,
            "TERM404_1",
            "존재하지 않은 약관입니다."),
    REQUIRED_TERM_NOT_AGREED(HttpStatus.BAD_REQUEST,
            "TERM400_1",
            "필수 약관에 동의해야 합니다."),
    FOOD_NOT_FOUND(HttpStatus.NOT_FOUND,
            "FOOD404_1",
            "존재하지 않는 음식 종류입니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
