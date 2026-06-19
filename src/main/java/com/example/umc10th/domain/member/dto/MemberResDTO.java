package com.example.umc10th.domain.member.dto;

import com.example.umc10th.domain.member.enums.Gender;
import com.example.umc10th.domain.member.enums.SocialType;
import com.example.umc10th.domain.mission.dto.MissionResDTO;
import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

public class MemberResDTO {
    @Builder
    public record SignUpResponse(
            Long memberId,
            String name,
            String nickname,
            String email,
            Gender gender
    ) {
    }

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

    @Builder

    public record GetInfo(
            Long memberId,
            String name,
            String nickname,
            String email,
            String phoneNumber,
            String profileImageUrl,
            LocalDate birth,
            String address,
            Integer point,
            Gender gender,
            SocialType socialType

    ) {
    }

    @Builder
    public record Login(
            String accessToken
    ) {
    }
}
