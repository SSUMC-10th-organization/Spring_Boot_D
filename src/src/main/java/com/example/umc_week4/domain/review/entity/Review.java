package com.example.umc_week4.domain.review.entity;

import com.example.umc_week4.domain.member.entity.Member;
import com.example.umc_week4.domain.mission.entity.Store;
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
@Table(name = "review")
public class Review extends BaseEntity {


    //ERD의 리뷰 테이블
    //id, member_id, store_id, body, score존재함
    // 리뷰가 사라지면 사진, 답글도 같이 지워져야함
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;

    @Column(name = "body")
    private String body;

    @Column(name = "score")
    private Float score;

    @Builder.Default
    @OneToMany(mappedBy = "review")
    private List<ReviewPhoto> reviewPhotoList = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "review")
    private List<Reply> replyList = new ArrayList<>();
}