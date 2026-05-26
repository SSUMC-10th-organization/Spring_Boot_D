package com.example.umc10thmission4.domain.mission.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

public class MissionResDTO {
    @Builder
    @Getter
    @NoArgsConstructor @AllArgsConstructor
    public static class MissionListDTO {
        List<MissionDetailDTO> missionList;
        Integer listSize;
    }

    @Builder @Getter @NoArgsConstructor
    @AllArgsConstructor
    public static class MissionDetailDTO {
        Long missionId;
        String title;
        String status;
    }

    //가게 내 미션 조회
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
            Integer pageNumber,
            Integer pageSize
    ){}

    @Builder
    public record OngoingMission(
            Long missionId,
            String storeName,
            Integer point,
            String conditional,
            LocalDate deadline
    ) {}
}
