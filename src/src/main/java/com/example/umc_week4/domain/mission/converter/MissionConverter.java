package com.example.umc_week4.domain.mission.converter;

import com.example.umc_week4.domain.mission.dto.MissionReqDTO;
import com.example.umc_week4.domain.mission.dto.MissionResDTO;
import com.example.umc_week4.domain.mission.entity.Mission;
import com.example.umc_week4.domain.mission.entity.Store;
import com.example.umc_week4.domain.mission.entity.mapping.MemberMission;
import org.springframework.data.domain.Page;

import java.util.List;

public class MissionConverter {

    // 가게 미션 생성
    public static Mission toMission(
            Store store,
            MissionReqDTO.CreateMission dto
    ) {
        return Mission.builder()
                .store(store)
                .conditional(dto.conditional())
                .point(dto.point())
                .deadline(dto.deadline())
                .build();
    }

    // 가게 내 미션 조회
    public static MissionResDTO.GetMission toGetMission(
            Mission mission
    ) {
        return MissionResDTO.GetMission.builder()
                .conditional(mission.getConditional())
                .point(mission.getPoint())
                .missionId(mission.getId())
                .build();
    }

    // 페이지네이션 틀 생성
    public static <T> MissionResDTO.Pagination<T> toPagination(
            List<T> data,
            Boolean hasNext,
            String nextCursor,
            Integer pageSize
    ){
        return MissionResDTO.Pagination.<T>builder()
                .data(data)
                .hasNext(hasNext)
                .nextCursor(nextCursor)
                .pageSize(pageSize)
                .build();
    }

    // 오프셋 페이지네이션 틀 생성
    public static <T> MissionResDTO.OffsetPagination<T> toOffsetPagination(
            Page<T> page
    ) {
        return MissionResDTO.OffsetPagination.<T>builder()
                .data(page.getContent())
                .pageNumber(page.getNumber())
                .pageSize(page.getSize())
                .totalPages(page.getTotalPages())
                .totalElements(page.getTotalElements())
                .hasNext(page.hasNext())
                .build();
    }

    // 내가 진행중인 미션 조회
    public static MissionResDTO.MyMission toMyMission(
            MemberMission memberMission
    ) {
        Mission mission = memberMission.getMission();
        return MissionResDTO.MyMission.builder()
                .missionId(mission.getId())
                .storeId(mission.getStore().getId())
                .storeName(mission.getStore().getName())
                .point(mission.getPoint())
                .conditional(mission.getConditional())
                .status(memberMission.getStatus().name())
                .build();
    }
}
