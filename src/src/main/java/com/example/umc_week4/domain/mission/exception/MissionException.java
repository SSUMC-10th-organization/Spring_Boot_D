package com.example.umc_week4.domain.mission.exception;

import com.example.umc_week4.global.apiPayload.code.BaseErrorCode;
import com.example.umc_week4.global.apiPayload.exception.ProjectException;

public class MissionException extends ProjectException {
    public MissionException(BaseErrorCode code) {
        super(code);
    }
}
