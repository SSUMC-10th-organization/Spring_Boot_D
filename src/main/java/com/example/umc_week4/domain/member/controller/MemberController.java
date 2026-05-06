package com.example.umc_week4.domain.member.controller;

import com.example.umc_week4.domain.member.dto.MemberReqDTO;
import com.example.umc_week4.domain.member.dto.MemberResDTO;
import com.example.umc_week4.domain.member.exception.code.MemberSuccessCode;
import com.example.umc_week4.domain.member.service.MemberService;
import com.example.umc_week4.global.apiPayload.ApiResponse;
import com.example.umc_week4.global.apiPayload.code.BaseSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/auth/signup")
    public ApiResponse<MemberResDTO.SignupResult> signup(
            @RequestBody MemberReqDTO.Signup request
    ) {
        BaseSuccessCode code = MemberSuccessCode.SIGNUP_SUCCESS;

        MemberResDTO.SignupResult result = new MemberResDTO.SignupResult(
                request.username(),
                request.email()
        );

        return ApiResponse.onSuccess(code, result);
    }

    /**
     * 마이페이지 화면 조회 API입니다.
     * 단순 name 조건 조회이므로 Service 내부에서 findByName()을 사용합니다.
     */
    @GetMapping("/users/me")
    public ApiResponse<MemberResDTO.GetInfo> getMyPage(
            @RequestParam(defaultValue = "nickname012") String name
    ) {
        BaseSuccessCode code = MemberSuccessCode.OK;
        MemberResDTO.GetInfo result = memberService.getMyPage(name);

        return ApiResponse.onSuccess(code, result);
    }

    @GetMapping("/home")
    public ApiResponse<MemberResDTO.HomeInfo> getHome(
            @RequestParam(defaultValue = "1") Long memberId,
            @RequestParam(defaultValue = "안암동") String locationName,
            @RequestParam(defaultValue = "0") Long cursor,
            @RequestParam(defaultValue = "15") Integer size
    ) {
        BaseSuccessCode code = MemberSuccessCode.HOME_SUCCESS;
        MemberResDTO.HomeInfo result = memberService.getHome(
                memberId,
                locationName,
                cursor,
                size
        );

        return ApiResponse.onSuccess(code, result);
    }
}