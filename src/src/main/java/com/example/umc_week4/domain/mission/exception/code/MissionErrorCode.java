package com.example.umc_week4.domain.mission.exception.code;

import com.example.umc_week4.global.apiPayload.code.BaseErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum MissionErrorCode implements BaseErrorCode {

    QUERY_NOT_VALID(HttpStatus.BAD_REQUEST,
            "MISSION400_1",
            "유효하지 않은 조회 기준입니다."),

    STATUS_NOT_VALID(HttpStatus.BAD_REQUEST,
            "MISSION400_2",
            "유효하지 않은 미션 상태입니다."),

    CURSOR_NOT_VALID(HttpStatus.BAD_REQUEST,
            "MISSION400_3",
            "유효하지 않은 커서입니다."),

    NOT_FOUND(HttpStatus.NOT_FOUND,
            "MISSION404_1",
            "해당 미션이 존재하지 않습니다."),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
