package com.example.umc10thmission4.domain.member.exception;

import com.example.umc10thmission4.global.apiPayload.code.BaseErrorCode;

public class MenberException extends RuntimeException {
    public MenberException(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
