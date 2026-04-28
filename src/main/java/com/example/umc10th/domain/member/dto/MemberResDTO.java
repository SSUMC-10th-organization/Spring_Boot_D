package com.example.umc10th.domain.member.dto;

import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class MemberResDTO {

    // 홈 화면 DTO

    @Builder
    public record HomeResultDTO(
            MemberSummaryDTO memberInfo,
            List<ActiveMissionDTO> activeMissions
    ) {}

    // member + address 테이블 정보 조합
    @Builder
    public record MemberSummaryDTO(
            String nickName,
            Long point,
            String addressName,
            String detailAddress
    ) {}

    // member_mission 테이블 정보
    @Builder
    public record ActiveMissionDTO(
            Long memberMissionId,
            Boolean isCompleted,
            MissionInfoDTO missionInfo
    ) {}

    // mission 테이블 정보
    @Builder
    public record MissionInfoDTO(
            Long missionId,
            String goal,
            Long award,
            LocalDate deadline
    ) {}

    // 회원가입

    @Builder
    public record JoinResultDTO(
            Long memberId,
            LocalDateTime createdAt
    ) {}
}
