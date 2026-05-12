package com.example.umc10th.domain.mission.entity;

import com.example.umc10th.domain.mission.entity.mapping.MemberMission;
import com.example.umc10th.global.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Mission extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer rewardPoint;

    @Column(nullable = false)
    private Integer requiredAmount;

    @Column(nullable = false)
    private String conditionText;

    private LocalDateTime deadline;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;

    @Builder.Default
    @OneToMany(mappedBy = "mission")
    private List<MemberMission> memberMissionList = new ArrayList<>();
}
