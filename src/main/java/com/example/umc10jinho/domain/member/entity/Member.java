package com.example.umc10jinho.domain.member.entity;

import com.example.umc10jinho.domain.market.entity.Region;
import com.example.umc10jinho.domain.member.entity.mapping.MemberFood;
import com.example.umc10jinho.domain.member.entity.mapping.MemberMission;
import com.example.umc10jinho.domain.member.entity.mapping.MemberTerm;
import com.example.umc10jinho.domain.member.enums.Gender;
import com.example.umc10jinho.domain.member.enums.SocialType;
import com.example.umc10jinho.domain.review.entity.Review;
import com.example.umc10jinho.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "member")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 20)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Gender gender;

    @Column(nullable = false)
    private LocalDate birth;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Column(nullable = false, length = 200)
    private String address;

    @Column(length = 200)
    private String detailAddress;

    @Column(nullable = false)
    @Builder.Default
    private Integer point = 0;

    @Enumerated(EnumType.STRING)
    private SocialType socialType;

    @Column(length = 100)
    private String socialId;

    @Column(length = 300)
    private String profileImage;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id")
    private Region region;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    @Builder.Default
    private List<MemberTerm> memberTerms = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    @Builder.Default
    private List<MemberFood> memberFoods = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    @Builder.Default
    private List<MemberMission> memberMissions = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    @Builder.Default
    private List<Review> reviews = new ArrayList<>();
}
