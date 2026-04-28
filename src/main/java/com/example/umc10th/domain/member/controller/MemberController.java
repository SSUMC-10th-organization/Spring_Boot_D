package com.example.umc10th.domain.member.controller;

import com.example.umc10th.domain.member.dto.MemberReqDTO;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.GeneralSuccessCode;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberController {

    @GetMapping("/home")
    public ApiResponse<String> getHome() {
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, "성공");
    }

    @PostMapping("auth/signup")
    public ApiResponse<String> signup(
            @RequestBody MemberReqDTO.JoinDTO request) {
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, "성공");
    }
}
