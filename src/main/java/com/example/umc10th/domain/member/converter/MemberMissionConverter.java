package com.example.umc10th.domain.member.converter;

import com.example.umc10th.domain.member.entity.mapping.MemberMission;
import com.example.umc10th.domain.mission.dto.MissionResponse;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MemberMissionConverter {
    // 1. 단건 Entity -> DTO 변환
    public static MissionResponse.MemberMissionDTO toMemberMissionDTO(MemberMission memberMission) {
        return new MissionResponse.MemberMissionDTO(
                memberMission.getId(),
                memberMission.getMission().getId(),
                memberMission.getMission().getStore().getName(),
                memberMission.getMission().getGoal(),
                memberMission.getMission().getAward(),
                memberMission.getIsCompleted(),
                memberMission.getCreatedAt()
        );
    }

    // 2. Page<Entity> -> 최종 응답 List DTO 변환 조립
    public static MissionResponse.MemberMissionListDTO toMemberMissionListDTO(Page<MemberMission> memberMissionPage) {
        // Page 안의 요소들을 단건 변환 메서드를 사용해 리스트로 변환
        List<MissionResponse.MemberMissionDTO> missionDTOList = memberMissionPage.stream()
                .map(memberMission -> toMemberMissionDTO(memberMission))
                .collect(Collectors.toList());

        // 최종 페이징 메타데이터와 함께 조립
        return new MissionResponse.MemberMissionListDTO(
                missionDTOList,
                missionDTOList.size(),
                memberMissionPage.getTotalPages(),
                memberMissionPage.getTotalElements(),
                memberMissionPage.isFirst(),
                memberMissionPage.isLast()
        );
    }
}
