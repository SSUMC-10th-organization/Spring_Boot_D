package com.example.umc10th.domain.mission.repository;

import com.example.umc10th.domain.mission.entity.mapping.MemberMission;
import com.example.umc10th.domain.mission.enums.MissionStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {

    Long countByMemberId(Long memberId);

    Long countByMemberIdAndStatus(Long memberId, MissionStatus status);

    @Query(
            value = """
                    select mm
                    from MemberMission mm
                    join fetch mm.mission m
                    join fetch m.store s
                    where mm.member.id = :memberId
                      and mm.status = :status
                      and mm.deletedAt is null
                    order by mm.createdAt desc
                    """,
            countQuery = """
                    select count(mm)
                    from MemberMission mm
                    where mm.member.id = :memberId
                      and mm.status = :status
                      and mm.deletedAt is null
                    """
    )
    Page<MemberMission> findMyMissions(
            @Param("memberId") Long memberId,
            @Param("status") MissionStatus status,
            Pageable pageable
    );
}