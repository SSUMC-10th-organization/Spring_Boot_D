package com.example.umc10th.domain.member.entity;

import com.example.umc10th.domain.member.entity.mapping.MemberFood;
import com.example.umc10th.domain.member.entity.mapping.MemberTerm;
import com.example.umc10th.domain.member.enums.Gender;
import com.example.umc10th.domain.member.enums.SocialType;
import com.example.umc10th.domain.mission.entity.mapping.MemberMission;
import com.example.umc10th.domain.review.entity.Review;
import com.example.umc10th.global.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String nickname;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    private String phoneNumber;

    private String profileImageUrl;

    private LocalDate birth;

    private String address;

    @Builder.Default
    @Column(nullable = false)
    private Integer point = 0;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Enumerated(EnumType.STRING)
    private SocialType socialType;

    // OAuth 로그인에서 제공하는 고유 ID
    private String socialUid;

    @Builder.Default
    @OneToMany(mappedBy = "member")
    private List<MemberFood> memberFoodList = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "member")
    private List<MemberTerm> memberTermList = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "member")
    private List<MemberMission> memberMissionList = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "member")
    private List<Review> reviewList = new ArrayList<>();

    public void addPoint(Integer point) {
        this.point += point;
    }
}