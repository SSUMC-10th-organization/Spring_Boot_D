package com.example.umc_week4.domain.member.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.util.List;

public class MemberResDTO {

    @Builder
    public record RequestBody(
            String stringTest,
            Long longTest
    ) {
    }

    @Builder
    public record GetInfo(
            String name,
            String email,
            @JsonProperty("phone_number")
            String phoneNumber,
            Integer point
    ) {
    }

    public record SignupResult(
            String username,
            String email
    ) {
    }

    public record Login(
            String accessToken
    ) {
    }

    public record HomeInfo(
            String location,

            @JsonProperty("total_completed_count")
            Long totalCompletedCount,

            Long cursor,

            @JsonProperty("has_next")
            Boolean hasNext,

            List<HomeMission> missions
    ) {
    }

    public record HomeMission(
            @JsonProperty("mission_id")
            Long missionId,

            @JsonProperty("store_id")
            Long storeId,

            @JsonProperty("store_name")
            String storeName,

            @JsonProperty("mission_condition")
            String missionCondition,

            @JsonProperty("mission_reward")
            Integer missionReward,

            @JsonProperty("cursor_value")
            Long cursorValue
    ) {
    }
}