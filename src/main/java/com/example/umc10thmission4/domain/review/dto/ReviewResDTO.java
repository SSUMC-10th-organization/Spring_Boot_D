package com.example.umc10thmission4.domain.review.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

public class ReviewResDTO {
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateResultDTO {
        Long reviewId;
        LocalDateTime createdAt;
    }

    @Builder
    public record GetReview(
            Long reviewId,
            String body,
            Integer ratings,
            String createdAt,
            String storeName
    ) {}

    @Builder
    public record Pagination<T>(
            List<T> data,
            Boolean hasNext,
            String nextCursor,
            Integer pageSize
    ) {}
}
