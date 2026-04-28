package com.example.umc10th.domain.mission.dto;

import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class MissionResDTO {

    // 미션 리스트

    @Builder
    public record MissionListDTO(
            List<MissionPreviewDTO> missionList
    ) {}

    @Builder
    public record MissionPreviewDTO(
            Long memberMissionId,
            StoreInfoDTO storeInfo,
            MissionInfoDTO missionInfo,
            Boolean isCompleted
    ) {}

    @Builder
    public record StoreInfoDTO(
            Long storeId,
            String storeName
    ) {}

    @Builder
    public record MissionInfoDTO(
            String goal,
            Long award,
            LocalDate deadline
    ) {}


    // 미션 성공 처리

    @Builder
    public record MissionCompleteResultDTO(
            Long memberMissionId,
            Boolean isCompleted,
            LocalDateTime completedAt
    ) {}
}
