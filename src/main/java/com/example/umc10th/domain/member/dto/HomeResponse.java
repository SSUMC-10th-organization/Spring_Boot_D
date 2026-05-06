package com.example.umc10th.domain.member.dto;

import java.time.LocalDate;
import java.util.List;

public class HomeResponse {

    // 단건 미션 데이터 응답
    public record AvailableMissionDTO(
            Long missionId,
            Long storeId,
            String storeName,
            String goal,
            Long award,
            LocalDate deadline
    ) {}

    // 페이징된 미션 리스트 데이터 응답 (페이징 메타데이터 포함)
    public record AvailableMissionListDTO(
            List<AvailableMissionDTO> missionList,
            Integer listSize,
            Integer totalPage,
            Long totalElements,
            Boolean isFirst,
            Boolean isLast
    ) {}
}