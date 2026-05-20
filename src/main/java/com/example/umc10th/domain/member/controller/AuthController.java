package com.example.umc10th.domain.member.controller;

import com.example.umc10th.domain.member.dto.MemberReqDTO;
import com.example.umc10th.domain.member.dto.MemberResDTO;
import com.example.umc10th.domain.member.exception.code.MemberSuccessCode;
import com.example.umc10th.domain.member.service.MemberService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final MemberService memberService;

    @PostMapping("/signup")
    public ApiResponse<MemberResDTO.SignUpResponse> signUp(
            @RequestBody @Valid MemberReqDTO.SignUpRequest request
    ) {
        return ApiResponse.onSuccess(
                MemberSuccessCode.SIGN_UP,
                memberService.signUp(request)
        );
    }
}
