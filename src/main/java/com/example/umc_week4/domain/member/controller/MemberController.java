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
//        BaseSuccessCode code = MemberSuccessCode.HOME_SUCCESS;
//        public MemberResDTO.SignupResult signup(MemberReqDTO.Signup request) {
//            // TODO: 다음 주차에 구현
//            throw new UnsupportedOperationException("아직 미구현");
//        }
//
//        public MemberResDTO.HomeInfo getHomeInfo(String authorization) {
//            // TODO: 다음 주차에 구현
//            throw new UnsupportedOperationException("아직 미구현");
//        }
//
//
        return null;
    }
}