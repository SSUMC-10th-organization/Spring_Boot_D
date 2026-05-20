package com.example.umc10th.domain.review.exception.code;

import com.example.umc10th.global.apiPayload.code.BaseSuccesscode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ReviewSuccessCode implements BaseSuccesscode {

    OK(HttpStatus.OK,
            "REVIEW200_1",
            "성공적으로 리뷰를 조회했습니다."),

    CREATED(HttpStatus.OK,
            "REVIEW200_2",
            "성공적으로 답글을 작성했습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}