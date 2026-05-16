package com.example.umc10th.domain.mission.exception.code;

import com.example.umc10th.global.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum MissionErrorCode implements BaseErrorCode {

    QUERY_NOT_VALID(HttpStatus.BAD_REQUEST, "MISSION4001", "지원하지 않는 조회 조건입니다."),
    STORE_NOT_FOUND(HttpStatus.NOT_FOUND, "MISSION4002", "존재하지 않는 가게입니다."),
    MISSION_NOT_FOUND(HttpStatus.NOT_FOUND, "MISSION4003", "존재하지 않는 미션입니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
