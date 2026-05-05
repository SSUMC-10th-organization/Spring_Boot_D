package com.example.umc_week4.domain.mission.exception.code;

import com.example.umc_week4.global.apiPayload.code.BaseSuccessCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum MissionSuccessCode implements BaseSuccessCode {

    GET_MISSION_LIST_SUCCESS(
            HttpStatus.OK,
            "MISSION200_1",
            "미션 목록을 조회했습니다."
    ),

    UPDATE_MISSION_STATUS_SUCCESS(
            HttpStatus.OK,
            "MISSION200_2",
            "미션 상태가 수정되었습니다."
    );

    private final HttpStatus status;
    private final String code;
    private final String message;
}