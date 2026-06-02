package com.example.umc10thmission4.domain.member.converter;

import com.example.umc10thmission4.domain.member.dto.MemberReqDTO;
import com.example.umc10thmission4.domain.member.dto.MemberResDTO;
import com.example.umc10thmission4.domain.member.entity.Member;
import com.example.umc10thmission4.domain.member.enums.Address;
import com.example.umc10thmission4.domain.member.enums.Gender;
import com.example.umc10thmission4.domain.member.enums.SocialProvider;
import com.example.umc10thmission4.global.security.dto.OAuthDTO; // import 추가

import java.time.LocalDate;

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
                .gender(Gender.valueOf(dto.gender()))
                .birth(LocalDate.parse(dto.birth()))
                .address(Address.valueOf(dto.address()))
                .socialProvider(SocialProvider.LOCAL)
                .build();
    }

    // OAuthDTO를 Member 엔티티로 변환 (추가)
    public static Member toMember(OAuthDTO dto) {
        return Member.builder()
                .name(dto.getName())
                .email(dto.getSocialEmail())
                .password("")                        // OAuth는 비밀번호 없음
                .socialUid(dto.getSocialUid())
                .socialProvider(dto.getSocialProvider())
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

    // Login 응답 DTO 변환 (추가)
    public static MemberResDTO.Login toLogin(String accessToken) {
        return MemberResDTO.Login.builder()
                .accessToken(accessToken)
                .build();
    }
}