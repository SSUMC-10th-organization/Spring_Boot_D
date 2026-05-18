package com.example.umc_week4.domain.mission.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class MissionReqDTO {

    // 가게 미션 생성
    public record CreateMission(
            @NotNull(message = "마감기한은 필수입니다.")
            LocalDate deadline,
            @NotNull(message = "미션 성공 포인트는 필수입니다.")
            Integer point,
            @NotBlank(message = "조건은 빈칸일 수 없습니다.")
            String conditional
    ){}

    // 내가 진행중인 미션 조회
    public record GetMyMissions(
            @NotNull(message = "사용자 ID는 필수입니다.")
            @JsonProperty("member_id")
            Long memberId,

            @NotBlank(message = "미션 상태는 필수입니다.")
            String status,

            @NotNull(message = "페이지 번호는 필수입니다.")
            @Min(value = 0, message = "페이지 번호는 0 이상이어야 합니다.")
            Integer pageNumber,

            @NotNull(message = "페이지 크기는 필수입니다.")
            @Min(value = 1, message = "페이지 크기는 1 이상이어야 합니다.")
            Integer pageSize,

            String sort
    ) {}

    // 미션 상태 변경
    public record UpdateStatus(
            @NotBlank(message = "미션 상태는 필수입니다.")
            String status,

            @NotNull(message = "가게 ID는 필수입니다.")
            @JsonProperty("store_id")
            Long storeId,

            @NotBlank(message = "가게 이름은 필수입니다.")
            @JsonProperty("store_name")
            String storeName
    ) {
    }
}
