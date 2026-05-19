package com.example.umc10jinho.global.security.handler;

import com.example.umc10jinho.global.apiPayload.ApiResponse;
import com.example.umc10jinho.global.apiPayload.code.BaseErrorCode;
import com.example.umc10jinho.global.apiPayload.code.GeneralErrorCode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void commence(
            HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException authException
    ) throws IOException {
        String accept = request.getHeader("Accept");
        String requestUri = request.getRequestURI();

        // 이미 /login 계열이면 루프 방지 - 그냥 401 반환
        if (requestUri.startsWith("/login")) {
            writeUnauthorized(response);
            return;
        }

        // 브라우저 요청(HTML)이면 로그인 페이지로 리다이렉트
        if (accept != null && accept.contains("text/html")) {
            response.sendRedirect("/login");
            return;
        }

        // REST 요청이면 401 JSON 반환
        writeUnauthorized(response);
    }

    private void writeUnauthorized(HttpServletResponse response) throws IOException {
        BaseErrorCode code = GeneralErrorCode.UNAUTHORIZED;
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(code.getStatus().value());
        objectMapper.writeValue(response.getOutputStream(), ApiResponse.onFailure(code, null));
    }
}
