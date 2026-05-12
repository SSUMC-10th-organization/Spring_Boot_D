package com.example.umc10th.domain.mission.converter;

import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.entity.Mission;
import com.example.umc10th.domain.mission.entity.mapping.MemberMission;
import com.example.umc10th.domain.mission.enums.MissionStatus;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class MissionConverter {

    public static MissionResDTO.MissionPreviewDTO toMissionPreviewDTO(MemberMission memberMission) {
        Mission mission = memberMission.getMission();

        String buttonText = memberMission.getStatus() == MissionStatus.COMPLETED
                ? "리뷰 남기기"
                : "미션 도전!";

        return MissionResDTO.MissionPreviewDTO.builder()
                .memberMissionId(memberMission.getId())
                .missionId(mission.getId())
                .storeName(mission.getStore().getName())
                .storeCategory(mission.getStore().getCategory())
                .rewardPoint(mission.getRewardPoint())
                .requiredAmount(mission.getRequiredAmount())
                .conditionText(mission.getConditionText())
                .status(memberMission.getStatus())
                .buttonText(buttonText)
                .build();
    }

    public static MissionResDTO.HomeMissionDTO toHomeMissionDTO(Mission mission) {
        return MissionResDTO.HomeMissionDTO.builder()
                .missionId(mission.getId())
                .storeName(mission.getStore().getName())
                .storeCategory(mission.getStore().getCategory())
                .rewardPoint(mission.getRewardPoint())
                .requiredAmount(mission.getRequiredAmount())
                .conditionText(mission.getConditionText())
                .dDayText(calculateDday(mission))
                .build();
    }

    public static MissionResDTO.MissionPreviewListResponse toMissionPreviewListResponse(Page<MemberMission> page) {
        List<MissionResDTO.MissionPreviewDTO> missionList = page.getContent()
                .stream()
                .map(MissionConverter::toMissionPreviewDTO)
                .toList();

        return MissionResDTO.MissionPreviewListResponse.builder()
                .missionList(missionList)
                .page(page.getNumber())
                .size(page.getSize())
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .isFirst(page.isFirst())
                .isLast(page.isLast())
                .build();
    }

    private static String calculateDday(Mission mission) {
        if (mission.getDeadline() == null) {
            return null;
        }

        long days = ChronoUnit.DAYS.between(LocalDateTime.now().toLocalDate(), mission.getDeadline().toLocalDate());

        if (days < 0) {
            return "마감";
        }

        return "D-" + days;
    }
}
