package com.example.umc_week4.domain.member.converter;

import com.example.umc_week4.domain.member.dto.MemberReqDTO;
import com.example.umc_week4.domain.member.dto.MemberResDTO;
import com.example.umc_week4.domain.member.entity.Member;
import com.example.umc_week4.domain.member.enums.Gender;
import com.example.umc_week4.domain.member.exception.MemberException;
import com.example.umc_week4.domain.member.exception.code.MemberErrorCode;
import jakarta.validation.constraints.NotBlank;

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

    public static MemberResDTO.GetInfo toGetInfo(
            Member member
    ) {
        return MemberResDTO.GetInfo.builder()
                .name(member.getName())
                .email(member.getEmail())
                .phoneNumber(member.getPhoneNumber())
                .point(member.getPoint())
                .build();
    }

    public static MemberResDTO.Login toLogin(
            String accessToken
    ) {
        return new MemberResDTO.Login(accessToken);
    }

    public static MemberResDTO.RequestBody requestBody(
            MemberReqDTO.RequestBody dto
    ) {
        return MemberConverter.toRequestBody(dto.stringTest(), dto.longTest());
    }

    public static Member toMember(
            MemberReqDTO.Signup request,
            String encodedPassword
    ) {
        return Member.builder()
                .name(request.username())
                .gender(toGender(request.gender()))
                .birth(request.birth())
                .city(request.address())
                .detailAddress(request.detailAddress())
                .phoneNumber(request.phoneNumber())
                .email(request.email())
                .password(encodedPassword)
                .build();
    }

    private static Gender toGender(String gender) {
        return switch (gender) {
            case "MALE", "남", "남성" -> Gender.MALE;
            case "FEMALE", "여", "여성" -> Gender.FEMALE;
            case "NONE", "선택안함" -> Gender.NONE;
            default -> throw new MemberException(MemberErrorCode.INVALID_GENDER);
        };
    }
}