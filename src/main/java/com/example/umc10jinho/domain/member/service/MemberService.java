package com.example.umc10jinho.domain.member.service;

import com.example.umc10jinho.domain.member.dto.MemberReqDTO;
import com.example.umc10jinho.domain.member.entity.Member;
import com.example.umc10jinho.domain.member.enums.Gender;
import com.example.umc10jinho.domain.member.exception.MemberException;
import com.example.umc10jinho.domain.member.exception.code.MemberErrorCode;
import com.example.umc10jinho.domain.member.repository.MemberRepository;
import com.example.umc10jinho.global.apiPayload.code.GeneralErrorCode;
import com.example.umc10jinho.global.apiPayload.exception.ProjectException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public Member signUp(MemberReqDTO.SignUpRequest request) {
        if (memberRepository.findByEmail(request.email()).isPresent()) {
            throw new MemberException(MemberErrorCode.DUPLICATE_EMAIL);
        }

        Member member = Member.builder()
                .name(request.name())
                .gender(Gender.valueOf(request.gender().toUpperCase()))
                .birth(LocalDate.parse(request.born()))
                .email(request.email())
                .password(passwordEncoder.encode(request.password()))
                .address(request.address())
                .build();

        return memberRepository.save(member);
    }

    public Member getMyInfo(Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new ProjectException(GeneralErrorCode.NOT_FOUND));
    }
}
