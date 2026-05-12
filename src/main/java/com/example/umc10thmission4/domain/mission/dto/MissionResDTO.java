package com.example.umc10thmission4.domain.mission.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
}
