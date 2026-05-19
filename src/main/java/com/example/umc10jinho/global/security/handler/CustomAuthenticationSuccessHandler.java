package com.example.umc10jinho.global.security.handler;

import com.example.umc10jinho.domain.member.converter.MemberConverter;
import com.example.umc10jinho.domain.member.dto.MemberResDTO;
import com.example.umc10jinho.domain.member.exception.code.MemberSuccessCode;
import com.example.umc10jinho.global.apiPayload.ApiResponse;
import com.example.umc10jinho.global.security.entity.AuthMember;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication
    ) throws IOException {
        AuthMember authMember = (AuthMember) authentication.getPrincipal();
        MemberResDTO.SignUpResponse body = MemberConverter.toSignUpResponse(authMember.getMember());

        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(MemberSuccessCode.LOGIN.getStatus().value());

        ApiResponse<MemberResDTO.SignUpResponse> apiResponse = ApiResponse.onSuccess(MemberSuccessCode.LOGIN, body);
        objectMapper.writeValue(response.getOutputStream(), apiResponse);
    }
}
