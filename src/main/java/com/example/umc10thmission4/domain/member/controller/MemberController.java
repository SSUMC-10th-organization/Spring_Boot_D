package com.example.umc10thmission4.domain.member.controller;

import com.example.umc10thmission4.domain.member.dto.MemberReqDTO;
import com.example.umc10thmission4.domain.member.dto.MemberResDTO;
import com.example.umc10thmission4.domain.member.exception.code.MemberSuccessCode;
import com.example.umc10thmission4.domain.member.service.MemberService;
import com.example.umc10thmission4.global.apiPayload.ApiResponse;
import com.example.umc10thmission4.global.apiPayload.code.BaseSuccessCode;
import com.example.umc10thmission4.global.security.entity.AuthMember;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping
public class MemberController {

    private final MemberService memberService;

    // 마이페이지
    @GetMapping("/v2/users/me")
    public ApiResponse<MemberResDTO.GetInfo> getInfo(
            @AuthenticationPrincipal AuthMember member
    ) {
        BaseSuccessCode code = MemberSuccessCode.OK;
        return ApiResponse.onSuccess(memberService.getInfo(member));
    }

    //회원 가입
    @PostMapping("/auth/signup")
    public ApiResponse<MemberResDTO.JoinResultDTO> join(
            @RequestBody MemberReqDTO.JoinDTO request
    ) {
        return ApiResponse.onSuccess(memberService.joinMember(request));
    }

    //로그인
    /*@PostMapping("/auth/users/login") // 클래스 상단에 @RequestMapping("/auth")가 있다고 가정
    public ApiResponse<MemberResDTO.LoginResultDTO> login(
            @RequestBody MemberReqDTO.LoginDTO request
    ) {
        return ApiResponse.onSuccess(MemberSuccessCode.OK, memberService.login(request));
    }*/
}
