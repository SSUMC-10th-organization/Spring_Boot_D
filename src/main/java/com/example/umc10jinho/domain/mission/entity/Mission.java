package com.example.umc10jinho.domain.mission.entity;

import com.example.umc10jinho.domain.market.entity.Market;
import com.example.umc10jinho.domain.member.entity.mapping.MemberMission;
import com.example.umc10jinho.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "mission")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Mission extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer point;

    @Column(nullable = false)
    private LocalDate deadline;

    @Column(nullable = false, length = 500)
    private String missionSpec;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "market_id")
    private Market market;

    @OneToMany(mappedBy = "mission")
    @Builder.Default
    private List<MemberMission> memberMissions = new ArrayList<>();
}
