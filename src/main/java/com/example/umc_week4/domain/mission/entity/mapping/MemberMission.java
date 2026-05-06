package com.example.umc_week4.domain.mission.entity.mapping;

import com.example.umc_week4.domain.member.entity.Member;
import com.example.umc_week4.domain.mission.entity.Mission;
import com.example.umc_week4.global.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "member_mission")
public class MemberMission extends BaseEntity {

    public enum Status {
        CHALLENGING,
        COMPLETE,
        OVERDUE
    }


    //ERD의 member mission 매핑테이블
    //id, memeberid, missionid, status존재함
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mission_id")
    private Mission mission;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

//    public void updateStatus(Status status) {
//        this.status = status;
//    }
}