package com.example.umc10th.domain.member.converter;

import com.example.umc10th.domain.member.dto.MemberResDTO;
import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.mission.dto.MissionResDTO;

import java.util.List;

public class MemberConverter {

    public static MemberResDTO.MyPageResponse toMyPageResponse(Member member, Long reviewCount) {
        return MemberResDTO.MyPageResponse.builder()
                .memberId(member.getId())
                .nickname(member.getNickname())
                .email(member.getEmail())
                .phoneNumber(member.getPhoneNumber())
                .profileImageUrl(member.getProfileImageUrl())
                .point(member.getPoint())
                .reviewCount(reviewCount)
                .build();
    }

    public static MemberResDTO.HomeResponse toHomeResponse(
            Member member,
            Long totalMissionCount,
            Long completedMissionCount,
            List<MissionResDTO.HomeMissionDTO> missionList
    ) {
        return MemberResDTO.HomeResponse.builder()
                .memberId(member.getId())
                .nickname(member.getNickname())
                .point(member.getPoint())
                .totalMissionCount(totalMissionCount)
                .completedMissionCount(completedMissionCount)
                .progressValue(completedMissionCount.intValue())
                .progressTotal(10)
                .missionList(missionList)
                .build();
    }
}
