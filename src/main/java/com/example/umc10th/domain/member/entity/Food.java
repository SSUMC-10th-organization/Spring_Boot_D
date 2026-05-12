package com.example.umc10th.domain.member.entity;

import com.example.umc10th.domain.member.entity.mapping.MemberFood;
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
public class Food extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Builder.Default
    @OneToMany(mappedBy = "food")
    private List<MemberFood> memberFoodList = new ArrayList<>();
}
