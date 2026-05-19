package com.example.umc10jinho.domain.review.dto;

import java.time.LocalDateTime;
import java.util.List;

public class ReviewResDTO {

    public record WriteReviewResponse(
            Long reviewId,
            String marketName,
            String body,
            Float score,
            LocalDateTime createdAt
    ) {
    }

    public record MyReviewPreview(
            Long reviewId,
            String marketName,
            String body,
            Float score,
            LocalDateTime createdAt
    ) {
    }

    public record MyReviewListResponse(
            List<MyReviewPreview> reviews,
            boolean hasNext,
            Long lastId,
            Float lastScore
    ) {
    }
}
