package com.example.umc10thmission4.domain.member.exception.code;

import com.example.umc10thmission4.global.apiPayload.code.BaseErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum MemberErrorCode implements BaseErrorCode {

    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "MEMBER404", "해당 유저를 찾을 수 없습니다."),
    INVALID_PASSWORD(HttpStatus.UNAUTHORIZED, "MEMBER401", "비밀번호가 일치하지 않습니다."),
    NOT_SUPPORT_SOCIAL_PROVIDER(HttpStatus.BAD_REQUEST, "MEMBER400", "지원하지 않는 소셜 로그인입니다.");// 추가;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
