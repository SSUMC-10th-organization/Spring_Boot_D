package com.example.umc10th.domain.mission.converter;

import com.example.umc10th.domain.mission.dto.MissionReqDTO;
import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.entity.Mission;
import com.example.umc10th.domain.mission.entity.Store;
import com.example.umc10th.domain.mission.entity.mapping.MemberMission;
import com.example.umc10th.domain.mission.enums.MissionStatus;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
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

    // 홈 화면 미션 조회용 DTO 변환
    public static MissionResDTO.HomeMissionDTO toHomeMissionDTO(Mission mission) {
        return MissionResDTO.HomeMissionDTO.builder()
                .missionId(mission.getId())
                .storeName(mission.getStore().getName())
                .storeCategory(mission.getStore().getCategory())
                .point(mission.getPoint())
                .conditional(mission.getConditional())
                .deadline(mission.getDeadline())
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

    // 내가 진행중인 미션 조회 DTO 변환
    public static MissionResDTO.OngoingMissionDTO toOngoingMissionDTO(MemberMission memberMission) {
        Mission mission = memberMission.getMission();

        return MissionResDTO.OngoingMissionDTO.builder()
                .memberMissionId(memberMission.getId())
                .missionId(mission.getId())
                .storeName(mission.getStore().getName())
                .storeCategory(mission.getStore().getCategory())
                .point(mission.getPoint())
                .conditional(mission.getConditional())
                .deadline(mission.getDeadline())
                .status(memberMission.getStatus())
                .build();
    }

    // 오프셋 기반 페이지네이션 응답 생성
    public static <T> MissionResDTO.OffsetPagination<T> toOffsetPagination(Page<T> page) {
        return MissionResDTO.OffsetPagination.<T>builder()
                .data(page.getContent())
                .pageNumber(page.getNumber())
                .pageSize(page.getSize())
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .isFirst(page.isFirst())
                .isLast(page.isLast())
                .build();
    }
}
