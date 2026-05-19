package com.example.umc10thmission4.domain.store.exception.code;

import com.example.umc10thmission4.global.apiPayload.code.BaseErrorCode;
import org.springframework.http.HttpStatus;

public enum StoreErrorCode implements BaseErrorCode {

    NOT_FOUND(HttpStatus.NOT_FOUND,
            "STORE404_1",
            "해당 가게가 존재하지 않습니다.");

    StoreErrorCode(HttpStatus status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }

    private final HttpStatus status;
    private final String code;
    private final String message;

    @Override
    public HttpStatus getStatus() {
        return this.status;
    }

    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
