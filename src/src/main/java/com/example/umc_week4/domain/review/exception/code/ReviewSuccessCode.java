package com.example.umc_week4.domain.review.exception.code;

import com.example.umc_week4.global.apiPayload.code.BaseSuccessCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ReviewSuccessCode implements BaseSuccessCode {

    CREATE_REVIEW_SUCCESS(
            HttpStatus.CREATED,
            "REVIEW201_1",
            "리뷰가 작성되었습니다."
    ),

    OK(
            HttpStatus.OK,
            "REVIEW200_1",
            "성공적으로 리뷰를 조회했습니다."
    );

    private final HttpStatus status;
    private final String code;
    private final String message;
}
