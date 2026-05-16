package com.example.umc10thmission4.domain.member.entity.mapping;

import com.example.umc10thmission4.domain.common.BaseEntity;
import com.example.umc10thmission4.domain.member.entity.Member;
import com.example.umc10thmission4.domain.mission.entity.Mission;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "member_mission")
public class MemberMission extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "state", nullable = true)
    private Long state;

    @Column(name = "complete_at", nullable = true)
    private LocalDateTime completeAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mission_id", nullable = false)
    private Mission mission;
}