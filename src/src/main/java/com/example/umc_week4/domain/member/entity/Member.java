package com.example.umc_week4.domain.member.entity;

import com.example.umc_week4.domain.member.entity.mapping.MemberFood;
import com.example.umc_week4.domain.member.entity.mapping.MemberTerm;
import com.example.umc_week4.domain.member.enums.Gender;
import com.example.umc_week4.domain.member.enums.SocialType;
import com.example.umc_week4.domain.member.enums.Status;
import com.example.umc_week4.domain.mission.entity.mapping.MemberMission;
import com.example.umc_week4.domain.review.entity.Review;
import com.example.umc_week4.global.BaseEntity;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "member")
public class Member extends BaseEntity {


    //ERD의 멤버 테이블
    //id, name, gender, birth, address, detail_address
    // status, point, email, phone_number, social_type
    // social_uid존재함
    // 또한 양방향 매핑을 위한 관계 정의도 있음
    // -> 멤버가 사라지면 선호음식, 약관, 미션 리스트 삭제를 위함
    // 리뷰는 냅두자
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private Gender gender;

    @Column(name = "birth")
    private LocalDate birth;

    @Column(name = "address")
    private String city;

    @Column(name = "detail_address")
    private String detailAddress;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    @Builder.Default
    private Status status = Status.ACTIVE;

    @Column(name = "point")
    @Builder.Default
    private Integer point = 0;

    @Column(name = "email")
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "social_type")
    private SocialType socialType;

    @Column(name = "social_uid")
    private String socialUid;

    @Column(nullable = false)
    private String password;

    @Builder.Default
    @OneToMany(mappedBy = "member")
    private List<MemberFood> memberFoodList = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "member")
    private List<MemberTerm> memberTermList = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "member")
    private List<MemberMission> memberMissionList = new ArrayList<>();

//    @Builder.Default
//    @OneToMany(mappedBy = "member")
//    private List<Review> reviewList = new ArrayList<>();
}