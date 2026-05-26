package com.example.umc10th.domain.member.exception.code;

import com.example.umc10th.global.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;


@Getter
@AllArgsConstructor
public enum MemberErrorCode implements BaseErrorCode {

    NOT_FOUND(HttpStatus.NOT_FOUND,
            "COMMON404_1",
            "요청한 멤버를 찾을 수 없습니다."),

    FORBIDDEN(HttpStatus.FORBIDDEN,
            "COMMON404_2",
            "입력 정보를 다시 확인해주세요.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
