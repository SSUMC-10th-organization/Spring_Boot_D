package com.example.umc10jinho.domain.member.exception;

import com.example.umc10jinho.global.apiPayload.code.BaseErrorCode;
import com.example.umc10jinho.global.apiPayload.exception.ProjectException;

public class MemberException extends ProjectException {

    public MemberException(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
