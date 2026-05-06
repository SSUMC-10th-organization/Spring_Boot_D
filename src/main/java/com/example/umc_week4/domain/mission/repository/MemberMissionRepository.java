package com.example.umc_week4.domain.mission.repository;

import com.example.umc_week4.domain.mission.entity.mapping.MemberMission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {

      @Query(value = "SELECT " +
            "m.id AS mission_id, " +
            "m.reward_point, " +
            "mm.status, " +
            "m.id AS cursor_value " +
            "FROM member_mission mm " +
            "JOIN mission m ON mm.mission_id = m.id " +
            "WHERE mm.member_id = :memberId " +
            "AND mm.status = :status " +
            "AND m.id < :cursor " +
            "ORDER BY m.id DESC " +
            "LIMIT :size", nativeQuery = true)
    List<Object[]> findMyMissions(
            @Param("memberId") Long memberId,
            @Param("status") String status,
            @Param("cursor") Long cursor,
            @Param("size") Integer size
    );

    Long countByMember_IdAndStatus(Long memberId, MemberMission.Status status);
}