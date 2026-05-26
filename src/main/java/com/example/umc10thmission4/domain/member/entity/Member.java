package com.example.umc10thmission4.domain.member.entity;

import com.example.umc10thmission4.domain.common.BaseEntity;
import com.example.umc10thmission4.domain.member.entity.mapping.MemberFood;
import com.example.umc10thmission4.domain.member.entity.mapping.MemberMission;
import com.example.umc10thmission4.domain.member.entity.mapping.MemberTerm;
import com.example.umc10thmission4.domain.member.enums.Address;
import com.example.umc10thmission4.domain.member.enums.Gender;
import com.example.umc10thmission4.domain.member.enums.SocialProvider;
import com.example.umc10thmission4.domain.review.entity.Review;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
@Table(name = "member")
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "gender", nullable = true)
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private Gender gender = Gender.NONE;

    @Column(name = "birth", nullable = true)
    private LocalDate birth;

    @Column(name = "address", nullable = true)
    @Enumerated(EnumType.STRING)
    private Address address;

    @Column(name = "detail_address", nullable = true)
    private String detailAddress;

    @Column(name = "social_uid", nullable = true)
    private String socialUid;

    @Column(name = "social_provider", nullable = false)
    @Enumerated(EnumType.STRING)
    private SocialProvider socialProvider;

    @Builder.Default
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<MemberFood> memberFoodList = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<MemberTerm> memberTermList = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<MemberMission> memberMissionList = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Review> reviewList = new ArrayList<>();
}
