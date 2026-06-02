package com.example.umc10thmission4.domain.member.service;

import com.example.umc10thmission4.domain.member.converter.MemberConverter;
import com.example.umc10thmission4.domain.member.dto.MemberReqDTO;
import com.example.umc10thmission4.domain.member.dto.MemberResDTO;
import com.example.umc10thmission4.domain.member.entity.Member;
import com.example.umc10thmission4.domain.member.repository.MemberRepository;
import com.example.umc10thmission4.global.security.entity.AuthMember;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    // 마이페이지
    public MemberResDTO.GetInfo getInfo(AuthMember member) {
        // 컨버터를 이용해서 응답 DTO 생성 & return
        return MemberConverter.toGetInfo(member.getMember());
    }

    public MemberResDTO.JoinResultDTO joinMember(MemberReqDTO.JoinDTO dto) {

        String encodedPassword = passwordEncoder.encode(dto.password());

        Member member = MemberConverter.toMember(dto, encodedPassword);

        memberRepository.save(member);

        return MemberConverter.toJoinResult(member);
    }
}