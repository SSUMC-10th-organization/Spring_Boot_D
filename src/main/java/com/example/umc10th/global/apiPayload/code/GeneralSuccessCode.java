package com.example.umc10th.global.apiPayload.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum GeneralSuccessCode implements BaseSuccessCode {

    // 200 OK: 일반적인 성공
    OK(HttpStatus.OK,
            "COMMON200",
            "요청에 성공했습니다."),

    // 201 Created: 생성 성공
    CREATED(HttpStatus.CREATED,
            "COMMON201",
            "리소스 생성에 성공했습니다."),

    // 202 Accepted: 요청이 접수되었으나 처리가 완료되지 않음
    ACCEPTED(HttpStatus.ACCEPTED,
            "COMMON202",
            "요청이 성공적으로 접수되었습니다."),

    // 204 No Content: 성공했으나 응답 본문(Body)에 내용이 없음
    NO_CONTENT(HttpStatus.NO_CONTENT,
            "COMMON204",
            "성공했으나 응답할 내용이 없습니다."),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
