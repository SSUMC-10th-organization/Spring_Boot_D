package com.example.umc10th.domain.member.exception.code;

import com.example.umc10th.global.apiPayload.code.BaseSuccesscode;
import org.springframework.http.HttpStatus;

public enum MemberSuccessCode implements BaseSuccesscode {

    SIGN_UP(HttpStatus.OK, "MEMBER200_1", "성공적으로 회원가입했습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;

    MemberSuccessCode(HttpStatus status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }

    @Override
    public HttpStatus getStatus() {
        return status;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
