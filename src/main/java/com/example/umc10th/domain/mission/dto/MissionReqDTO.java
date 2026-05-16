package com.example.umc10th.domain.mission.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

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

    // 내가 진행중인 미션 조회 - 오프셋 기반 페이지네이션

    public record OngoingMissionRequest(

            @NotNull(message = "사용자 ID는 필수입니다.")
            Long memberId,
            @NotNull(message = "페이지 번호는 필수입니다.")
            @Min(value = 0, message = "페이지 번호는 0 이상이어야 합니다.")
            Integer pageNumber,
            @NotNull(message = "페이지 크기는 필수입니다.")
            @Min(value = 1, message = "페이지 크기는 1 이상이어야 합니다.")
            Integer pageSize,
            @Pattern(regexp = "id|createdAt", message = "정렬 기준은 id 또는 createdAt만 가능합니다.")
            String sort
    ) {}
}
