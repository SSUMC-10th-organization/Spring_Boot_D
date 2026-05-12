package com.example.umc10jinho.domain.member.exception.code;

import com.example.umc10jinho.global.apiPayload.code.BaseSuccessCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum MemberSuccessCode implements BaseSuccessCode {

    SIGN_UP(
            HttpStatus.CREATED,
            "MEMBER201_1",
            "회원가입에 성공했습니다."
    ),

    LOGIN(
            HttpStatus.OK,
            "MEMBER200_1",
            "로그인에 성공했습니다."
    ),

    GET_MY_INFO(
            HttpStatus.OK,
            "MEMBER200_2",
            "내 정보 조회에 성공했습니다."
    );

    private final HttpStatus status;
    private final String code;
    private final String message;
}
