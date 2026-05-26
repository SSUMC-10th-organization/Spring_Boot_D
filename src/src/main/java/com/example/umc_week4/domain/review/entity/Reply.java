package com.example.umc_week4.domain.review.entity;

import com.example.umc_week4.global.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "review_reply")
public class Reply extends BaseEntity {


    //ERD의 답글 테이블
    //id, review_id, content존재함
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "review_id")
    private Review review;

    @Column(name = "content")
    private String content;
}