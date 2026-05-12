package com.example.umc10jinho.domain.member.converter;

import com.example.umc10jinho.domain.member.dto.MemberResDTO;
import com.example.umc10jinho.domain.member.entity.Member;

public class MemberConverter {

    public static MemberResDTO.MyInfoResponse toMyInfoResponse(Member member) {
        return new MemberResDTO.MyInfoResponse(
                member.getId(),
                member.getName(),
                member.getGender().name(),
                member.getEmail(),
                member.getAddress(),
                member.getPoint()
        );
    }
}
