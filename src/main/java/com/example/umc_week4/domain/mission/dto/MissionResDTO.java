package com.example.umc_week4.domain.mission.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class MissionResDTO {

    // 미션 목록 조회 응답
    public record MissionList(
            String status,
            Integer page,
            Integer size,

            @JsonProperty("has_next")
            Boolean hasNext,

            List<MissionInfo> missions
    ) {
    }

    // 미션 정보
    public record MissionInfo(
            @JsonProperty("mission_id")
            Long missionId,

            @JsonProperty("store_id")
            Long storeId,

            @JsonProperty("store_name")
            String storeName,

            String title,

            @JsonProperty("reward_point")
            Integer rewardPoint,

            String status
    ) {
    }

    // 미션 상태 변경 응답
    public record UpdateMissionStatusResult(
            @JsonProperty("mission_id")
            Long missionId,

            @JsonProperty("store_id")
            Long storeId,

            @JsonProperty("store_name")
            String storeName,

            String status
    ) {
    }
}