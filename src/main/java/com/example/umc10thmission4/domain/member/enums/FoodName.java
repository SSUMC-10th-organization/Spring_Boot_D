package com.example.umc10thmission4.domain.member.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum FoodName {

    KOREAN("한식"),
    JAPANESE("일식"),
    CHINESE("중식"),
    WESTERN("양식"),
    CHICKEN("치킨"),
    SNACK("분식"), // 떡볶이, 김밥 등
    MEAT("고기/구이"),
    LUNCHBOX("도시락"),
    LATE_NIGHT("야식"),
    FASTFOOD("패스트푸드"),
    DESSERT("디저트"),
    ASIAN("아시안푸드");

    private final String description;
}
