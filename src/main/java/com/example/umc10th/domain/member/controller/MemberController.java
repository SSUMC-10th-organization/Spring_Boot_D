package com.example.umc10th.domain.member.controller;

import com.example.umc10th.domain.member.dto.MemberResDTO;
import com.example.umc10th.domain.member.service.MemberService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import lombok.RequiredArgsConstructor;
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
}
