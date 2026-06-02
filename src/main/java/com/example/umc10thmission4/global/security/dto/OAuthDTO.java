package com.example.umc10thmission4.global.security.dto;

import com.example.umc10thmission4.domain.member.enums.SocialProvider;

public interface OAuthDTO {
    SocialProvider getSocialProvider();
    String getSocialUid();
    String getSocialEmail();
    String getName();
}
