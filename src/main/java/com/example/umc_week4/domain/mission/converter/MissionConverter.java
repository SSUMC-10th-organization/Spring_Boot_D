package com.example.umc_week4.domain.mission.converter;

import com.example.umc_week4.domain.mission.dto.MissionResDTO;
import com.example.umc_week4.domain.mission.entity.Mission;

import java.util.List;

public class MissionConverter {

    //페이지네이션 틀 생성
    public static <T> MissionResDTO.Pagination<T> toPagination(
            List<T> data,
            Integer pageNumber,
            Integer pageSize
    ) {
        return MissionResDTO.Pagination.<T>builder()
                .data(data)
                .pageNumber(pageNumber)
                .pageSize(pageSize)
                .build();
    }

    public static MissionResDTO.GetMission toGetMission(Mission mission) {
    }
}
