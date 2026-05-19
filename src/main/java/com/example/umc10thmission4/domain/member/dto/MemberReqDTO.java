package com.example.umc10thmission4.domain.member.dto;

import lombok.Builder;

import java.util.List;

public class MemberReqDTO {

    //마이페이지
    public record GetInfo(
            Long id
    ){}

    //회원 가입
    @Builder
    public record JoinDTO(
            String name,
            String gender,
            String birth,
            String address,
            List<Long> foodCategories
    ){}

    //로그인
    @Builder
    public record LoginDTO(
            String email,
            String password
    ){}

}
