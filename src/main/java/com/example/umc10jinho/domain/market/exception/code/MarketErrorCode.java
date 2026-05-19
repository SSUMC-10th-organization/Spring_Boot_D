package com.example.umc10jinho.domain.market.exception.code;

import com.example.umc10jinho.global.apiPayload.code.BaseErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum MarketErrorCode implements BaseErrorCode {

    MARKET_NOT_FOUND(
            HttpStatus.NOT_FOUND,
            "MARKET404_1",
            "존재하지 않는 가게입니다."
    );

    private final HttpStatus status;
    private final String code;
    private final String message;
}
