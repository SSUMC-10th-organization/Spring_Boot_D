package com.example.umc10thmission4.domain.review.entity;

import com.example.umc10thmission4.domain.common.BaseEntity;
import com.example.umc10thmission4.domain.member.entity.Member;
import com.example.umc10thmission4.domain.review.entity.mapping.ReviewPhoto;
import com.example.umc10thmission4.domain.store.entity.Store;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Review extends BaseEntity { // 클래스 시작 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String body;

    private Float score;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;

    // 아까 바깥에 있던 녀석을 안으로 들여보냈습니다!
    @OneToMany(mappedBy = "review", cascade = CascadeType.ALL)
    private List<ReviewPhoto> reviewPhotoList = new ArrayList<>();

} // 클래스 끝 }