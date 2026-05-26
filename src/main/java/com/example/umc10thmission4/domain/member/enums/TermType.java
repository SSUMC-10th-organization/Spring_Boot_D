package com.example.umc10thmission4.domain.member.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum TermType {

    OVER_14_YEARS_OLD("만 14세 이상입니다.", true),
    SERVICE_TERMS("(필수)서비스 이용약관", true),
    PRIVACY_POLICY("(필수)개인 정보 처리 방침", true),
    LOCATION_INFO("(선택) 위치정보 제공", false),
    MARKETING_CONSENT("(선택) 마케팅 수신 동의", false);

    private final String title;     // 이미지에 표시된 약관 명칭
    private final boolean required; // 필수 동의 여부
}