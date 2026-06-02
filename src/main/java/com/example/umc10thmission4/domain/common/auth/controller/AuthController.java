package com.example.umc10thmission4.domain.common.auth.controller;

import com.example.umc10thmission4.domain.common.auth.dto.AuthReqDTO;
import com.example.umc10thmission4.domain.common.auth.dto.AuthResDTO;
import com.example.umc10thmission4.domain.common.auth.service.AuthService;
import com.example.umc10thmission4.global.apiPayload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ApiResponse<AuthResDTO.LoginResult> login(
            @RequestBody AuthReqDTO.LoginDTO request
    ) {
        return ApiResponse.onSuccess(authService.login(request));
    }
}
