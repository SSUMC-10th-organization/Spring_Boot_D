package com.example.umc10jinho.domain.member.controller;

import com.example.umc10jinho.domain.member.dto.MemberReqDTO;
import com.example.umc10jinho.domain.member.dto.MemberResDTO;
import com.example.umc10jinho.domain.member.exception.code.MemberSuccessCode;
import com.example.umc10jinho.global.apiPayload.ApiResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth/users")
public class AuthController {

    @PostMapping
    public ApiResponse<MemberResDTO.SignUpResponse> signUp(
            @RequestBody MemberReqDTO.SignUpRequest request
    ) {
        MemberResDTO.SignUpResponse response =
                new MemberResDTO.SignUpResponse(
                        1L,
                        request.name(),
                        request.email()
                );

        return ApiResponse.onSuccess(MemberSuccessCode.SIGN_UP, response);
    }

    @PostMapping("/login")
    public ApiResponse<MemberResDTO.LoginResponse> login(
            @RequestBody MemberReqDTO.LoginRequest request
    ) {
        MemberResDTO.LoginResponse response =
                new MemberResDTO.LoginResponse(
                        1L,
                        "temporary-access-token"
                );

        return ApiResponse.onSuccess(MemberSuccessCode.LOGIN, response);
    }
}