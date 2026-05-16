package com.example.umc_week4.domain.mission.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.util.List;

public class MissionResDTO {

    @Builder
    public record GetMission(
            Long missionId,
            Integer point,
            String conditional
    ){}

    //페이지 네이션 틀
    @Builder
    public record Pagination<T>(
            List<T> data,
            Integer pageNumber,
            Integer pageSize
    ){}

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