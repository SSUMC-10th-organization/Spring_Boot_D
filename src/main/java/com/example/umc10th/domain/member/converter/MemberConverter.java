package com.example.umc10th.domain.member.converter;

import com.example.umc10th.domain.member.dto.MemberReqDTO;
import com.example.umc10th.domain.member.dto.MemberResDTO;
import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.enums.SocialType;
import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.global.security.dto.OAuthDTO;

import java.util.List;

public class MemberConverter {
    public static Member toMember(MemberReqDTO.SignUpRequest request, String encodedPassword) {
        return Member.builder()
                .name(request.name())
                .nickname(request.nickname())
                .email(request.email())
                .password(encodedPassword)
                .phoneNumber(request.phoneNumber())
                .gender(request.gender())
                .birth(request.birth())
                .address(request.address())
                .socialType(SocialType.LOCAL)
                .socialUid(null)
                .point(0)
                .build();
    }

    public static Member toMember(OAuthDTO dto) {
        return Member.builder()
                .name(dto.getName())
                .nickname(dto.getName())
                .email(dto.getSocialEmail())
                .password(null)
                .phoneNumber(null)
                .profileImageUrl(null)
                .birth(null)
                .address(null)
                .gender(null)
                .socialType(dto.getSocialType())
                .socialUid(dto.getSocialUid())
                .point(0)
                .build();
    }

    public static MemberResDTO.SignUpResponse toSignUpResponse(Member member) {
        return MemberResDTO.SignUpResponse.builder()
                .memberId(member.getId())
                .name(member.getName())
                .nickname(member.getNickname())
                .email(member.getEmail())
                .gender(member.getGender())
                .build();
    }

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

    public static MemberResDTO.GetInfo toGetInfo(Member member) {

        return MemberResDTO.GetInfo.builder()
                .memberId(member.getId())
                .name(member.getName())
                .nickname(member.getNickname())
                .email(member.getEmail())
                .phoneNumber(member.getPhoneNumber())
                .profileImageUrl(member.getProfileImageUrl())
                .birth(member.getBirth())
                .address(member.getAddress())
                .point(member.getPoint())
                .gender(member.getGender())
                .socialType(member.getSocialType())
                .build();
    }

    public static MemberResDTO.Login toLogin(String accessToken) {
        return MemberResDTO.Login.builder()
                .accessToken(accessToken)
                .build();
    }
}
