package com.example.umc10thmission4.global.security.dto;

import com.example.umc10thmission4.domain.member.enums.SocialProvider;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class KakaoDTO implements OAuthDTO {

    private final String id;
    private final String email;
    private final String name;

    @Override
    public SocialProvider getSocialProvider() {
        return SocialProvider.KAKAO;
    }

    @Override
    public String getSocialUid() {
        return id;
    }

    @Override
    public String getSocialEmail() {
        return email;
    }

    @Override
    public String getName() {
        return name;
    }
}
