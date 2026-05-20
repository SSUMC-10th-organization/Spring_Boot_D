package com.example.umc10thmission4.domain.member.repository;

import com.example.umc10thmission4.domain.member.entity.mapping.MemberMission;
import com.example.umc10thmission4.domain.mission.enums.MissionStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {

    Page<MemberMission> findByMemberIdAndState(Long memberId, MissionStatus state, Pageable pageable);
}