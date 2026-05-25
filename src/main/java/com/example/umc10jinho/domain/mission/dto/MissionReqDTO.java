package com.example.umc10jinho.domain.mission.dto;

import jakarta.validation.constraints.NotNull;

public class MissionReqDTO {

    public record GetMyMissionsRequest(
            @NotNull(message = "회원 ID는 필수입니다.")
            Long memberId
    ) {
    }

    public record ChallengeMissionRequest(
            @NotNull(message = "미션 ID는 필수입니다.")
            Long missionId
    ) {
    }
}
