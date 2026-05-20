package com.example.umc_week4.domain.mission.repository;

import com.example.umc_week4.domain.mission.entity.mapping.MemberMission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {

    Page<MemberMission> findAllByMember_IdAndStatus(Long memberId, MemberMission.Status status, Pageable pageable);

    Long countByMember_IdAndStatus(Long memberId, MemberMission.Status status);
}
