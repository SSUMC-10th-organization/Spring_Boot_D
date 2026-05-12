package com.example.umc10th.domain.member.dto;

import com.example.umc10th.domain.mission.dto.MissionResDTO;
import lombok.Builder;

import java.util.List;

public class MemberResDTO {

    @Builder
    public record MyPageResponse(
            Long memberId,
            String nickname,
            String email,
            String phoneNumber,
            String profileImageUrl,
            Integer point,
            Long reviewCount
    ) {
    }

    @Builder
    public record HomeResponse(
            Long memberId,
            String nickname,
            Integer point,
            Long totalMissionCount,
            Long completedMissionCount,
            Integer progressValue,
            Integer progressTotal,
            List<MissionResDTO.HomeMissionDTO> missionList
    ) {
    }
}
