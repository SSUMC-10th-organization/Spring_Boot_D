package com.example.umc10th.domain.member.controller;

import com.example.umc10th.domain.member.dto.MemberResDTO;
import com.example.umc10th.domain.member.exception.code.MemberSuccessCode;
import com.example.umc10th.domain.member.service.MemberService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.BaseSuccesscode;
import com.example.umc10th.global.security.entity.AuthMember;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/{memberId}/my-page")
    public ApiResponse<MemberResDTO.MyPageResponse> getMyPage(
            @PathVariable Long memberId
    ) {
        return ApiResponse.onSuccess(memberService.getMyPage(memberId));
    }

    @GetMapping("/{memberId}/home")
    public ApiResponse<MemberResDTO.HomeResponse> getHome(
            @PathVariable Long memberId
    ) {
        return ApiResponse.onSuccess(memberService.getHome(memberId));
    }

    // 마이페이지
    @GetMapping("/v2/users/me")
    public ApiResponse<MemberResDTO.GetInfo> getInfo(
            @AuthenticationPrincipal AuthMember member
    ){
        BaseSuccesscode code = MemberSuccessCode.OK;
        return ApiResponse.onSuccess(code, memberService.getInfo(member));
    }
}
