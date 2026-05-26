package com.example.umc10thmission4.domain.mission.dto;

import com.example.umc10thmission4.domain.mission.enums.MissionStatus;
import lombok.Builder;

public class MissionReqDTO {
    //미션 상태
    @Builder
    public record MissionStatusDTO(
            MissionStatus status //enum: IN_PROGRESS, COMPLETED
    ){}
}
