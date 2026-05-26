package com.example.umc10thmission4.domain.member.controller;

import com.example.umc10thmission4.domain.member.dto.MemberReqDTO;
import com.example.umc10thmission4.domain.member.dto.MemberResDTO;
import com.example.umc10thmission4.domain.member.service.MemberService;
import com.example.umc10thmission4.global.apiPayload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping
public class MemberController {

    private final MemberService memberService;

    //마이페이지
    @PostMapping("/v1/users/me")
    public ApiResponse<MemberResDTO.GetInfo> getMemberInfo(@RequestBody MemberReqDTO.GetInfo request) {
        MemberResDTO.GetInfo response = memberService.getInfo(request);
        return ApiResponse.onSuccess(response);
    }

    //회원 가입
    /*@PostMapping("/auth/signin")
    public ApiResponse<MemberResDTO.JoinResultDTO> join(
            @RequestBody MemberReqDTO.JoinDTO request
    ) {
        return ApiResponse.onSuccess(MemberSuccessCode.OK, memberService.joinMember(request));
    }

    //로그인
    @PostMapping("/auth/users/login") // 클래스 상단에 @RequestMapping("/auth")가 있다고 가정
    public ApiResponse<MemberResDTO.LoginResultDTO> login(
            @RequestBody MemberReqDTO.LoginDTO request
    ) {
        return ApiResponse.onSuccess(MemberSuccessCode.OK, memberService.login(request));
    }*/
}
