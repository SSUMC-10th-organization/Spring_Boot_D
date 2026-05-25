package com.example.umc10jinho.domain.market.exception;

import com.example.umc10jinho.global.apiPayload.code.BaseErrorCode;
import com.example.umc10jinho.global.apiPayload.exception.ProjectException;

public class MarketException extends ProjectException {

    public MarketException(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
