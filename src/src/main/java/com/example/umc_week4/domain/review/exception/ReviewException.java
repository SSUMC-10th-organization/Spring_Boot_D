package com.example.umc_week4.domain.review.exception;

import com.example.umc_week4.global.apiPayload.code.BaseErrorCode;
import com.example.umc_week4.global.apiPayload.exception.ProjectException;

public class ReviewException extends ProjectException {
    public ReviewException(BaseErrorCode code) {
        super(code);
    }
}
