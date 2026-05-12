package com.example.umc10jinho.domain.mission.exception.code;

import com.example.umc10jinho.global.apiPayload.code.BaseSuccessCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum MissionSuccessCode implements BaseSuccessCode {

    GET_MY_MISSIONS(
            HttpStatus.OK,
            "MISSION200_1",
            "내 미션 목록 조회에 성공했습니다."
    ),

    GET_HOME_MISSIONS(
            HttpStatus.OK,
            "MISSION200_2",
            "홈 화면 미션 목록 조회에 성공했습니다."
    ),

    CHALLENGE_MISSION(
            HttpStatus.CREATED,
            "MISSION201_1",
            "미션 도전에 성공했습니다."
    );

    private final HttpStatus status;
    private final String code;
    private final String message;
}
