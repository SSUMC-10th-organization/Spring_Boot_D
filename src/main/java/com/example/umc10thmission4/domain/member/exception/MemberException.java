package com.example.umc10thmission4.domain.member.exception;

import com.example.umc10thmission4.global.apiPayload.code.BaseErrorCode;

public class MemberException extends RuntimeException {

    private final BaseErrorCode errorCode;

    public MemberException(BaseErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
