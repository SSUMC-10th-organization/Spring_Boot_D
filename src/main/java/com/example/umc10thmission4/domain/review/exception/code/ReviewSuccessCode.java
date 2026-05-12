package com.example.umc10thmission4.domain.review.exception.code;

import com.example.umc10thmission4.global.apiPayload.code.BaseSuccessCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ReviewSuccessCode implements BaseSuccessCode {

    OK(HttpStatus.OK,
            "Review200_1",
            "리뷰 관련 처리가 성공적으로 완료되었습니다."),
    ;
    private final HttpStatus status;
    private final String code;
    private final String message;
}
