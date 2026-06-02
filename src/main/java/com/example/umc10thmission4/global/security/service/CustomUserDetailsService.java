package com.example.umc10thmission4.global.security.service;

import com.example.umc10thmission4.domain.member.entity.Member;
import com.example.umc10thmission4.domain.member.enums.SocialProvider;
import com.example.umc10thmission4.domain.member.exception.MemberException;
import com.example.umc10thmission4.domain.member.exception.code.MemberErrorCode;
import com.example.umc10thmission4.domain.member.repository.MemberRepository;
import com.example.umc10thmission4.global.security.entity.AuthMember;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    public UserDetails loadUserByUidAndSocialType(
            SocialProvider socialProvider,
            String username
    ) throws UsernameNotFoundException {
        Member member = memberRepository.findBySocialProviderAndSocialUid(socialProvider, username)
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));
        return new AuthMember(member);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        throw new UsernameNotFoundException("사용하지 않는 메서드입니다.");
    }
}