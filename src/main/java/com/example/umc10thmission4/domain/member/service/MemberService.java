package com.example.umc10thmission4.domain.member.service;

import com.example.umc10thmission4.domain.member.converter.MemberConverter;
import com.example.umc10thmission4.domain.member.dto.MemberReqDTO;
import com.example.umc10thmission4.domain.member.dto.MemberResDTO;
import com.example.umc10thmission4.domain.member.entity.Member;
import com.example.umc10thmission4.domain.member.exception.MemberException;
import com.example.umc10thmission4.domain.member.exception.code.MemberErrorCode;
import com.example.umc10thmission4.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberResDTO.GetInfo getInfo(MemberReqDTO.GetInfo dto) {

        Long memberId = dto.id();

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

        return MemberConverter.toGetInfo(member);
    }
}