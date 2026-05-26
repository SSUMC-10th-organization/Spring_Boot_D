package com.example.umc_week4.domain.member.exception;

import com.example.umc_week4.global.apiPayload.exception.ProjectException;
import com.example.umc_week4.global.apiPayload.code.BaseErrorCode;

public class MemberException extends ProjectException {
    public MemberException(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
