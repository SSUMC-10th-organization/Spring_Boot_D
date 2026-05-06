package com.example.umc_week4.domain.mission.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class MissionResDTO {

    public record MissionList(
            String status,
            Long cursor,
            Integer size,

            @JsonProperty("has_next")
            Boolean hasNext,

            List<MissionInfo> missions
    ) {
    }

    public record MissionInfo(
            @JsonProperty("mission_id")
            Long missionId,

            @JsonProperty("reward_point")
            Integer rewardPoint,

            String status,

            @JsonProperty("cursor_value")
            Long cursorValue
    ) {
    }

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