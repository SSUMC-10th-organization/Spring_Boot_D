package com.example.umc10thmission4.domain.store.exception;

import com.example.umc10thmission4.global.apiPayload.code.BaseErrorCode;
import com.example.umc10thmission4.global.apiPayload.exception.ProjectException;

public class StoreException extends ProjectException {
    public StoreException(BaseErrorCode code) {
        super(code);
    }
}
