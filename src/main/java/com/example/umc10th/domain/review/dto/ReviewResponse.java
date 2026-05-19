package com.example.umc10th.domain.review.dto;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

public class ReviewResponse {

    public record WriteDTO(
            Long reviewId,
            LocalDateTime createdAt
    ) {}

    // 1. 개별 리뷰 정보 DTO
    @Builder
    public record ReviewDTO(
            Long reviewId,
            String storeName,
            String content,
            Float star,
            LocalDateTime createdAt
    ) {}

    // 2. 리뷰 전용 페이징 응답 DTO (MissionResDTO 대체)
    @Builder
    public record ReviewPreviewListDTO(
            List<ReviewDTO> reviewList,
            Integer listSize,
            Boolean hasNext,
            String nextCursor
    ) {}
}
