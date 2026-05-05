package com.example.umc_week4.domain.member.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.util.List;

public class MemberResDTO {

    // 기존 실습 코드 유지
    @Builder
    public record RequestBody(
            String stringTest,
            Long longTest
    ) {
    }

    // 기존 실습 코드 유지
    @Builder
    public record GetInfo(
            String name,
            String profileUrl,
            String email,
            String phoneNumber,
            Integer point
    ) {
    }

    // 회원가입 응답
    public record SignupResult(
            String username,
            String email
    ) {
    }

    // 홈 화면 응답
    public record HomeInfo(
            String address,
            Integer score,

            @JsonProperty("notification_enabled")
            Boolean notificationEnabled,

            @JsonProperty("completed_mission_count")
            Integer completedMissionCount,

            @JsonProperty("my_missions")
            List<MyMission> myMissions
    ) {
    }

    // 홈 화면의 진행중 미션
    public record MyMission(
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
}