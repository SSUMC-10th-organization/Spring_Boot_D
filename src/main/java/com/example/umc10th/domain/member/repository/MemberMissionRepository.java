package com.example.umc10th.domain.member.repository;

import com.example.umc10th.domain.member.entity.mapping.MemberMission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {

    // 특정 회원의 모든 미션(진행 중 + 완료)을 페이징하여 조회
    Page<MemberMission> findAllByMemberId(Long memberId, Pageable pageable);

    // 만약 진행 중 / 완료 상태별로 따로 모아본다면 아래 메서드 활용 가능
    Page<MemberMission> findAllByMemberIdAndIsCompleted(Long memberId, Boolean isCompleted, Pageable pageable);
}