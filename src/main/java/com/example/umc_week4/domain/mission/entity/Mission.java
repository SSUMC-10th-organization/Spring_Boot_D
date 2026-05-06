package com.example.umc_week4.domain.mission.entity;

import com.example.umc_week4.domain.mission.entity.mapping.MemberMission;
import com.example.umc_week4.global.BaseEntity;
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
@Table(name = "mission")
public class Mission extends BaseEntity {


    //ERD의 미션 테이블
    //id, store_id, point, deadline
    // mission_spec 존재함
    //미션 지워지면 멤버와 연관된 관계도 지워져야함
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;

    @Column(name = "reward_point")
    private Integer rewardPoint;

    @Column(name = "deadline")
    private LocalDate deadline;

    @Column(name = "mission_spec")
    private String missionSpec;

    @Builder.Default
    @OneToMany(mappedBy = "mission")
    private List<MemberMission> memberMissionList = new ArrayList<>();
}