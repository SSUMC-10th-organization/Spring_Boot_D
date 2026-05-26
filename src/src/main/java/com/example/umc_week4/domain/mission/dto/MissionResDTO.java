package com.example.umc_week4.domain.mission.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.util.List;

public class MissionResDTO {

    // 가게 내 미션 조회
    @Builder
    public record GetMission(
            Long missionId,
            Integer point,
            String conditional
    ){}

    // 페이지네이션 틀
    @Builder
    public record Pagination<T>(
            List<T> data,
            Boolean hasNext,
            String nextCursor,
            Integer pageSize
    ){}

    // 오프셋 페이지네이션 틀
    @Builder
    public record OffsetPagination<T>(
            List<T> data,
            Integer pageNumber,
            Integer pageSize,
            Integer totalPages,
            Long totalElements,
            Boolean hasNext
    ){}

    // 내가 진행중인 미션 조회
    @Builder
    public record MyMission(
            @JsonProperty("mission_id")
            Long missionId,

            @JsonProperty("store_id")
            Long storeId,

            @JsonProperty("store_name")
            String storeName,

            Integer point,

            String conditional,

            String status
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
