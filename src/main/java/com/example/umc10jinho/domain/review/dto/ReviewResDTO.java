package com.example.umc10jinho.domain.review.dto;

import java.time.LocalDateTime;

public class ReviewResDTO {

    public record WriteReviewResponse(
            Long reviewId,
            String marketName,
            String body,
            Float score,
            LocalDateTime createdAt
    ) {
    }
}
