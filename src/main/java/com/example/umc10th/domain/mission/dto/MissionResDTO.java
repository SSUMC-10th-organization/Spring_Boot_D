package com.example.umc10th.domain.mission.dto;

import com.example.umc10th.domain.mission.enums.MissionStatus;
import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
    ) {}

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
    ) {}

    @Builder
    public record HomeMissionDTO(
            Long missionId,
            String storeName,
            String storeCategory,
            Integer point,
            String conditional,
            LocalDate deadline
    ) {}

    // 가게 내 미션 조회
    @Builder
    public record GetMission(
            Long missionId,
            Integer point,
            String conditional
    ) {}

    // 페이지네이션 틀
    @Builder
    public record Pagination<T>(
            List<T> data,
            Boolean hasNext,
            String nextCursor,
            Integer pageSize
    ) {}

    // 내가 진행중인 미션 조회 응답
    @Builder
    public record OngoingMissionDTO(
            Long memberMissionId,
            Long missionId,
            String storeName,
            String storeCategory,
            Integer point,
            String conditional,
            LocalDate deadline,
            MissionStatus status
    ) {}

    // 오프셋 기반 페이지네이션 응답 틀
    @Builder
    public record OffsetPagination<T>(
            List<T> data,
            Integer pageNumber,
            Integer pageSize,
            Long totalElements,
            Integer totalPages,
            Boolean isFirst,
            Boolean isLast
    ) {}
}
