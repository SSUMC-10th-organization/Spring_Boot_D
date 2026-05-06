package com.example.umc10th.domain.member.converter;

import com.example.umc10th.domain.member.dto.HomeResponse;
import com.example.umc10th.domain.mission.entity.Mission;
import org.springframework.data.domain.Page;
import java.util.List;
import java.util.stream.Collectors;

public class HomeConverter {

    private HomeConverter() {}

    // 단건 Entity -> DTO 변환
    public static HomeResponse.AvailableMissionDTO toAvailableMissionDTO(Mission mission) {
        return new HomeResponse.AvailableMissionDTO(
                mission.getId(),
                mission.getStore().getId(),
                mission.getStore().getName(),
                mission.getGoal(),
                mission.getAward(),
                mission.getDeadline()
        );
    }

    // Page<Entity> -> 최종 페이징 리스트 응답 DTO 조립
    public static HomeResponse.AvailableMissionListDTO toAvailableMissionListDTO(Page<Mission> missionPage) {
        List<HomeResponse.AvailableMissionDTO> missionDTOList = missionPage.stream()
                .map(HomeConverter::toAvailableMissionDTO)
                .collect(Collectors.toList());

        return new HomeResponse.AvailableMissionListDTO(
                missionDTOList,
                missionDTOList.size(),
                missionPage.getTotalPages(),
                missionPage.getTotalElements(),
                missionPage.isFirst(),
                missionPage.isLast()
        );
    }
}