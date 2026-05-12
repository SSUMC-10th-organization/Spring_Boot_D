package com.example.umc10jinho.domain.review.exception.code;

import com.example.umc10jinho.global.apiPayload.code.BaseErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ReviewErrorCode implements BaseErrorCode {

    REVIEW_NOT_FOUND(
            HttpStatus.NOT_FOUND,
            "REVIEW404_1",
            "리뷰를 찾을 수 없습니다."
    ),

    INVALID_SCORE(
            HttpStatus.BAD_REQUEST,
            "REVIEW400_1",
            "평점은 0.0 이상 5.0 이하여야 합니다."
    );

    private final HttpStatus status;
    private final String code;
    private final String message;
}
