package com.example.umc_week4.domain.member.entity;

import com.example.umc_week4.domain.member.entity.mapping.MemberFood;
import com.example.umc_week4.domain.member.enums.FoodCategory;
import com.example.umc_week4.global.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "food")
public class Food extends BaseEntity {
    //ERD의 음식 종류테이블
    //id, name 존재함
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "name")
    private FoodCategory name;

    @Builder.Default
    @OneToMany(mappedBy = "food")
    private List<MemberFood> memberFoodList = new ArrayList<>();
}