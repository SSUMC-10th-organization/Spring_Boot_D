package com.example.umc10thmission4.domain.common.auth.service;

import com.example.umc10thmission4.domain.common.auth.dto.AuthReqDTO;
import com.example.umc10thmission4.domain.common.auth.dto.AuthResDTO;
import com.example.umc10thmission4.domain.member.entity.Member;
import com.example.umc10thmission4.domain.member.exception.MemberException;
import com.example.umc10thmission4.domain.member.exception.code.MemberErrorCode;
import com.example.umc10thmission4.domain.member.repository.MemberRepository;
import com.example.umc10thmission4.global.security.entity.AuthMember;
import com.example.umc10thmission4.global.security.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthResDTO.LoginResult login(AuthReqDTO.LoginDTO request) {

        // 이메일로 회원 조회
        Member member = memberRepository.findByEmail(request.email())
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

        // 비밀번호 검증
        if (!passwordEncoder.matches(request.password(), member.getPassword())) {
            throw new MemberException(MemberErrorCode.INVALID_PASSWORD);
        }

        // JWT 토큰 발급
        String accessToken = jwtUtil.createAccessToken(new AuthMember(member));

        return AuthResDTO.LoginResult.builder()
                .accessToken(accessToken)
                .build();
    }
}
