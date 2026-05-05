package com.example.umc_week4.domain.member.converter;

import com.example.umc_week4.domain.member.dto.MemberReqDTO;
import com.example.umc_week4.domain.member.dto.MemberResDTO;
import com.example.umc_week4.domain.member.entity.Member;

public class MemberConverter {
    public static MemberResDTO.RequestBody toRequestBody(
            String stringTest,
            Long longTest
    ) {
        return MemberResDTO.RequestBody.builder()
                .stringTest(stringTest)
                .longTest(longTest)
                .build();
    }

    //마이페이지
    public static MemberResDTO.GetInfo toGetInfo(
            Member member
    ){
        return MemberResDTO.GetInfo.builder()
                .email(member.getEmail())
                .name(member.getName())
                .point(member.getPoint())
                .phoneNumber(member.getPhoneNumber())
                .profileUrl(member.getProfileUrl())
                .build();
    }

    public static MemberResDTO.RequestBody requestBody(
            MemberReqDTO.RequestBody dto
    ) {
        return MemberConverter.toRequestBody(dto.stringTest(), dto.longTest());
    }
}
