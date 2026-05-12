package com.example.umc10jinho.domain.mission.dto;

import com.example.umc10jinho.domain.mission.enums.MissionStatus;

import java.time.LocalDate;
import java.util.List;

public class MissionResDTO {

    public record MyMissionPreview(
            Long memberMissionId,
            Long missionId,
            String missionSpec,
            Integer point,
            LocalDate deadline,
            String marketName,
            MissionStatus status
    ) {
    }

    public record MyMissionListResponse(
            List<MyMissionPreview> missions,
            int currentPage,
            int totalPages,
            long totalElements,
            boolean hasNext
    ) {
    }

    public record HomeMissionPreview(
            Long missionId,
            String missionSpec,
            Integer point,
            LocalDate deadline,
            String marketName,
            String marketCategory
    ) {
    }

    public record HomeMissionListResponse(
            List<HomeMissionPreview> missions,
            int currentPage,
            int totalPages,
            long totalElements,
            boolean hasNext
    ) {
    }
}
