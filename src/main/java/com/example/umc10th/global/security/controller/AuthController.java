package com.example.umc10th.global.security.controller;

import com.example.umc10th.domain.member.dto.MemberRequest;
import com.example.umc10th.domain.member.dto.MemberResponse;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.GeneralSuccessCode;
import com.example.umc10th.global.security.dto.AuthRequest;
import com.example.umc10th.global.security.dto.AuthResponse;
import com.example.umc10th.global.security.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/join")
    public ApiResponse<MemberResponse.JoinResultDTO> join(
            @RequestBody @Valid MemberRequest.JoinDTO request
    ) {
        MemberResponse.JoinResultDTO result = authService.signUp(request);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, result);
    }

    @PostMapping("/login")
    public ApiResponse<AuthResponse.LoginResultDTO> login(
            @RequestBody @Valid AuthRequest.LoginDTO request
    ) {
        AuthResponse.LoginResultDTO result = authService.login(request);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, result);
    }
}
