package com.example.umc_week4.domain.review.exception.code;

import com.example.umc_week4.global.apiPayload.code.BaseErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ReviewErrorCode implements BaseErrorCode {

    QUERY_NOT_VALID(HttpStatus.BAD_REQUEST,
            "REVIEW400_1",
            "유효하지 않은 조회 기준입니다."),

    CURSOR_NOT_VALID(HttpStatus.BAD_REQUEST,
            "REVIEW400_2",
            "유효하지 않은 커서입니다."),

    NOT_FOUND(HttpStatus.NOT_FOUND,
            "REVIEW404_1",
            "해당 리뷰가 존재하지 않습니다."),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
