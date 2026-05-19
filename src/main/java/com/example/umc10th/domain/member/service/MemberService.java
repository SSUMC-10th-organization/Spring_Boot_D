package com.example.umc10th.domain.member.service;

import com.example.umc10th.domain.member.converter.MemberConverter;
import com.example.umc10th.domain.member.dto.MemberRequest;
import com.example.umc10th.domain.member.dto.MemberResponse;
import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional(readOnly = true)
    public MemberResponse.MyPageDTO getMyPage(Long memberId) {
        // 1. 회원 검증 및 조회
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));

        // 2. Converter를 통해 Entity -> DTO 변환 후 반환
        return MemberConverter.toMyPageDTO(member);
    }

    @Transactional
    public Member join(MemberRequest.JoinDTO request) {
        // 1. 비밀번호 암호화
        String encodedPassword = passwordEncoder.encode(request.password());

        // 2. 컨버터를 이용해 엔티티 변환
        Member member = MemberConverter.toMember(request, encodedPassword);

        // 3. 저장
        return memberRepository.save(member);
    }
}
