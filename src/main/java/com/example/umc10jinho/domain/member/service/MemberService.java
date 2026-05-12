package com.example.umc10jinho.domain.member.service;

import com.example.umc10jinho.domain.member.entity.Member;
import com.example.umc10jinho.domain.member.repository.MemberRepository;
import com.example.umc10jinho.global.apiPayload.code.GeneralErrorCode;
import com.example.umc10jinho.global.apiPayload.exception.ProjectException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;

    public Member getMyInfo(Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new ProjectException(GeneralErrorCode.NOT_FOUND));
    }
}
