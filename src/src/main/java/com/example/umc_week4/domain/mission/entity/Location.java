package com.example.umc_week4.domain.mission.entity;

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
@Table(name = "location")
public class Location extends BaseEntity {


    //ERD의 지역 테이블
    //id, name
    // 양방향 매핑을 통해 가게가 지워지면 연관되어있는 것도 없앰
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Builder.Default
    @OneToMany(mappedBy = "location")
    private List<Store> storeList = new ArrayList<>();
}