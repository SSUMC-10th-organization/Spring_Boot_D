package com.example.umc10thmission4.domain.member.converter;

import com.example.umc10thmission4.domain.member.dto.MemberReqDTO;
import com.example.umc10thmission4.domain.member.dto.MemberResDTO;
import com.example.umc10thmission4.domain.member.entity.Member;
import com.example.umc10thmission4.domain.member.enums.SocialProvider;

public class MemberConverter {

    public static MemberResDTO.GetInfo toGetInfo(Member member) {
        return MemberResDTO.GetInfo.builder()
                .name(member.getName())
                .build();
    }

    // JoinDTO를 Member 엔티티로 변환
    public static Member toMember(MemberReqDTO.JoinDTO dto, String encodedPassword) {
        return Member.builder()
                .name(dto.name())
                .email(dto.email())
                .password(encodedPassword)
                .socialProvider(SocialProvider.LOCAL)
                .build();
    }

    // Member 엔티티를 JoinResultDTO로 변환
    public static MemberResDTO.JoinResultDTO toJoinResult(Member member) {
        return new MemberResDTO.JoinResultDTO(
                member.getId(),
                member.getCreatedAt(),
                member.getEmail()
        );
    }
}
