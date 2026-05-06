package com.example.umc_week4.domain.review.entity;

import com.example.umc_week4.global.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "review_photo")
public class ReviewPhoto extends BaseEntity {


    //ERD의 리뷰 사진테이블
    //id, review_id, photourl존재함
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "review_id")
    private Review review;

    @Column(name = "photo_url")
    private String photoUrl;
}