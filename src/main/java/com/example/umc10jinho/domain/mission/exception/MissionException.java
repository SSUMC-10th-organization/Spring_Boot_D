package com.example.umc10jinho.domain.mission.exception;

import com.example.umc10jinho.global.apiPayload.code.BaseErrorCode;
import com.example.umc10jinho.global.apiPayload.exception.ProjectException;

public class MissionException extends ProjectException {

    public MissionException(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
