package com.example.umc10th.global.security.service;

import com.example.umc10th.domain.member.dto.MemberRequest;
import com.example.umc10th.domain.member.dto.MemberResponse;
import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.exception.MemberException;
import com.example.umc10th.domain.member.exception.code.MemberErrorCode;
import com.example.umc10th.domain.member.repository.MemberRepository;
import com.example.umc10th.global.security.dto.AuthRequest;
import com.example.umc10th.global.security.dto.AuthResponse;
import com.example.umc10th.global.security.entity.AuthMember;
import com.example.umc10th.global.security.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Transactional
    public MemberResponse.JoinResultDTO signUp(MemberRequest.JoinDTO request) {
        // 1. 이메일 중복 체크
        if (memberRepository.existsByEmail(request.email())) {
            // 프로젝트 예외 처리 컨벤션에 맞춰 에러를 던집니다.
            throw new MemberException(MemberErrorCode.FORBIDDEN.getMessage());
        }

        // 2. 비밀번호 암호화
        String encodedPassword = passwordEncoder.encode(request.password());

        // 3. 기존 JoinDTO 구조 기반으로 Member 엔티티 빌드 및 저장
        Member member = Member.builder()
                .email(request.email())
                .password(encodedPassword) // 암호화된 비밀번호 주입
                .name(request.name())
                .nickname(request.nickname())
                .phoneNumber(request.phoneNumber())
                .birth(request.birth())
                .gender(request.gender())
                .address(request.address()) // RegionAddress enum 반영
                .point(0L) // 기본 포인트 세팅
                .build();

        Member savedMember = memberRepository.save(member);

        // 4. 기존 JoinResultDTO 형식에 맞춰 데이터 반환
        return new MemberResponse.JoinResultDTO(
                savedMember.getEmail(),
                savedMember.getName(),
                savedMember.getId()
        );
    }

    @Transactional
    public AuthResponse.LoginResultDTO login(AuthRequest.LoginDTO request) {
        // 1. 이메일로 회원 조회
        Member member = memberRepository.findByEmail(request.email())
                .orElseThrow(() -> new MemberException(MemberErrorCode.NOT_FOUND.getMessage()));

        // 2. 비밀번호 검증 (평문 비밀번호와 DB의 암호화된 비밀번호 비교)
        if (!passwordEncoder.matches(request.password(), member.getPassword())) {
            throw new MemberException(MemberErrorCode.FORBIDDEN.getMessage());
        }

        // 3. UserDetails 구현체인 AuthMember 생성
        AuthMember authMember = new AuthMember(member);

        // 4. Access Token 생성
        String accessToken = jwtUtil.createAccessToken(authMember);

        // 5. 결과 반환
        return new AuthResponse.LoginResultDTO(
                member.getEmail(),
                accessToken,
                "Bearer"
        );
    }
}