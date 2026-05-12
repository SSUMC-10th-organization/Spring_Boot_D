package com.example.umc10th.domain.mission.dto;

import com.example.umc10th.domain.mission.enums.MissionStatus;
import lombok.Builder;

import java.util.List;

public class MissionResDTO {

    @Builder
    public record MissionPreviewListResponse(
            List<MissionPreviewDTO> missionList,
            Integer page,
            Integer size,
            Long totalElements,
            Integer totalPages,
            Boolean isFirst,
            Boolean isLast
    ) {
    }

    @Builder
    public record MissionPreviewDTO(
            Long memberMissionId,
            Long missionId,
            String storeName,
            String storeCategory,
            Integer rewardPoint,
            Integer requiredAmount,
            String conditionText,
            MissionStatus status,
            String buttonText
    ) {
    }

    @Builder
    public record HomeMissionDTO(
            Long missionId,
            String storeName,
            String storeCategory,
            Integer rewardPoint,
            Integer requiredAmount,
            String conditionText,
            String dDayText
    ) {
    }
}
