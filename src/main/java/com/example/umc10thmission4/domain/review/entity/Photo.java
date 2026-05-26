package com.example.umc10thmission4.domain.review.entity;

import com.example.umc10thmission4.domain.common.BaseEntity;
import com.example.umc10thmission4.domain.review.entity.mapping.ReviewPhoto;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Photo extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String url; // 사진 저장 경로

    @Builder.Default
    @OneToMany(mappedBy = "photo", cascade = CascadeType.ALL)
    private List<ReviewPhoto> reviewPhotoList = new ArrayList<>();
}
