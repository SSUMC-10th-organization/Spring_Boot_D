package com.example.umc10th.domain.member.converter;

import com.example.umc10th.domain.member.dto.MemberResponse;
import com.example.umc10th.domain.member.entity.Member;

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
}
