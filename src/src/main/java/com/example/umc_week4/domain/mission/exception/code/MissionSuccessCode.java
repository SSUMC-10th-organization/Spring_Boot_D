package com.example.umc_week4.domain.mission.exception.code;

import com.example.umc_week4.global.apiPayload.code.BaseSuccessCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum MissionSuccessCode implements BaseSuccessCode {

    CREATED(HttpStatus.OK,
            "MISSION200_1",
            "성공적으로 미션을 생성했습니다."),

    OK(HttpStatus.OK,
            "MISSION200_2",
            "성공적으로 미션을 조회했습니다."),

    GET_MISSION_LIST_SUCCESS(
            HttpStatus.OK,
            "MISSION200_3",
            "미션 목록을 조회했습니다."
    ),

    UPDATE_MISSION_STATUS_SUCCESS(
            HttpStatus.OK,
            "MISSION200_4",
            "미션 상태가 수정되었습니다."
    );

    private final HttpStatus status;
    private final String code;
    private final String message;
}
