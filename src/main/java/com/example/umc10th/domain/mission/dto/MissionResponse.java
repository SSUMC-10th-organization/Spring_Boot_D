package com.example.umc10th.domain.mission.dto;

import java.time.LocalDateTime;
import java.util.List;

public class MissionResponse {

    // 단건 미션 데이터 응답
    public record MemberMissionDTO(
            Long memberMissionId,
            Long missionId,
            String storeName,  // 어느 가게의 미션인지 알 수 있도록 추가
            String goal,
            Long award,
            Boolean isCompleted, // 진행 중(false)인지 완료(true)인지 구분
            LocalDateTime createdAt
    ) {}

    // 페이징된 미션 리스트 데이터 응답 (페이징 메타데이터 포함)
    public record MemberMissionListDTO(
            List<MemberMissionDTO> missionList,
            Integer listSize,       // 현재 페이지의 데이터 개수
            Integer totalPage,      // 전체 페이지 수
            Long totalElements,     // 전체 데이터 개수
            Boolean isFirst,        // 첫 번째 페이지인지 여부
            Boolean isLast          // 마지막 페이지인지 여부
    ) {}
}
