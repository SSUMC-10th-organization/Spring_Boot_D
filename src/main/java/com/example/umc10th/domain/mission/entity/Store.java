package com.example.umc10th.domain.mission.entity;

import com.example.umc10th.domain.review.entity.Review;
import com.example.umc10th.global.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Store extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String category;

    private String detailAddress;

    private String phoneNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id")
    private Location location;

    @Builder.Default
    @OneToMany(mappedBy = "store")
    private List<Mission> missionList = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "store")
    private List<Review> reviewList = new ArrayList<>();
}
