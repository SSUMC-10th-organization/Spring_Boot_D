package com.example.umc10th.domain.member.converter;

import com.example.umc10th.domain.member.dto.MemberRequest;
import com.example.umc10th.domain.member.dto.MemberResponse;
import com.example.umc10th.domain.member.entity.Member;

import javax.management.relation.Role;

public class MemberConverter {

    private MemberConverter() {}

    public static MemberResponse.MyPageDTO toMyPageDTO(Member member) {
        return new MemberResponse.MyPageDTO(
                member.getId(),
                member.getName(),
                member.getNickname(),
                member.getEmail(),
                member.getPhoneNumber(),
                member.getPoint()
        );
    }

    public static Member toMember(MemberRequest.JoinDTO dto, String encodedPassword) {
        return Member.builder()
                .email(dto.email())
                .password(encodedPassword)
                .name(dto.name())
                .nickname(dto.nickname())
                .phoneNumber(dto.phoneNumber())
                .birth(dto.birth())
                .gender(dto.gender())
                .address(dto.address())
                .build();
    }

    // 회원 가입 결과
    public static MemberResponse.JoinResultDTO toJoinResultDTO(Member member) {
        return new MemberResponse.JoinResultDTO(
                member.getEmail(),
                member.getName(),
                member.getId()
        );
    }
}
