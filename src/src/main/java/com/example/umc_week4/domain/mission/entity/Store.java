package com.example.umc_week4.domain.mission.entity;

import com.example.umc_week4.domain.review.entity.Review;
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
@Table(name = "store")
public class Store extends BaseEntity {


    //ERD의 가게 테이블
    //id, name, location_id, address, detail_address존재함
    //가게가 지워지면 가게의 mission과, 가게에 작성된 리뷰도 삭제됨
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id")
    private Location location;

    @Column(name = "address")
    private String address;

    @Column(name = "detail_address")
    private String detailAddress;

    @Builder.Default
    @OneToMany(mappedBy = "store")
    private List<Mission> missionList = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "store")
    private List<Review> reviewList = new ArrayList<>();
}