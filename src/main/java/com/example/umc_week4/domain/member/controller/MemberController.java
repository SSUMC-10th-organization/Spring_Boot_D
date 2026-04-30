package com.example.umc_week4.domain.member.controller;

import com.example.umc_week4.domain.member.dto.MemberReqDTO;
import com.example.umc_week4.domain.member.dto.MemberResDTO;
import com.example.umc_week4.domain.member.exception.code.MemberSuccessCode;
import com.example.umc_week4.global.apiPayload.ApiResponse;
import com.example.umc_week4.global.apiPayload.code.BaseSuccessCode;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class MemberController {

    // 회원가입
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

    // 홈 화면 조회
    @GetMapping("/home")
    public ApiResponse<MemberResDTO.HomeInfo> getHome(
            @RequestHeader("Authorization") String authorization
    ) {
        BaseSuccessCode code = MemberSuccessCode.HOME_SUCCESS;

        MemberResDTO.HomeInfo result = new MemberResDTO.HomeInfo(
                "서울특별시 강남구",
                2500,
                true,
                3,
                List.of(
                        new MemberResDTO.MyMission(
                                1L,
                                10L,
                                "대야미 식당",
                                "점심 방문 후 리뷰 남기기",
                                500,
                                "ongoing"
                        )
                )
        );

        return ApiResponse.onSuccess(code, result);
    }
}