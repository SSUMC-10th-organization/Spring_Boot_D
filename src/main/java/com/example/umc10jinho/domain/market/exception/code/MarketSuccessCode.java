package com.example.umc10jinho.domain.market.exception.code;

import com.example.umc10jinho.global.apiPayload.code.BaseSuccessCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum MarketSuccessCode implements BaseSuccessCode {

    GET_HOME(
            HttpStatus.OK,
            "MARKET200_1",
            "홈 화면 조회에 성공했습니다."
    );

    private final HttpStatus status;
    private final String code;
    private final String message;
}
