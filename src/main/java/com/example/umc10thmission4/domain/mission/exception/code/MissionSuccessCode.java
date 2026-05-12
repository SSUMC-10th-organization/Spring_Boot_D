package com.example.umc10thmission4.domain.mission.exception.code;

import com.example.umc10thmission4.global.apiPayload.code.BaseSuccessCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum MissionSuccessCode implements BaseSuccessCode {

    OK(HttpStatus.OK,
            "Mission200_1",
            "미션 관련 처리가 성공적으로 완료되었습니다."),
    ;
    private final HttpStatus status;
    private final String code;
    private final String message;

}
