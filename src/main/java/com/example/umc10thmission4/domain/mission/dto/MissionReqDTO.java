package com.example.umc10thmission4.domain.mission.dto;

import com.example.umc10thmission4.domain.mission.enums.MissionStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.time.LocalDate;

public class MissionReqDTO {
    //미션 상태
    @Builder
    public record MissionStatusDTO(
            MissionStatus status //enum: IN_PROGRESS, COMPLETED
    ){}

    // 가게 미션 생성
    public record CreateMission(
            @NotNull(message = "마감기한은 필수입니다.")
            LocalDate deadline,
            @NotNull(message = "미션 성공 포인트는 필수입니다.")
            Integer point,
            @NotBlank(message = "조건은 빈칸일 수 없습니다.")
            String conditional
    ){}

    public record GetOngoingMissionsDTO(
            @NotNull(message = "사용자 ID는 필수입니다.")
            Long memberId,
            @NotNull(message = "페이지 크기는 필수입니다.")
            Integer pageSize,
            @NotNull(message = "페이지 번호는 필수입니다.")
            Integer pageNumber
    ) {}
}
