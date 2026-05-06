package com.example.umc10th.domain.review.dto;

import java.time.LocalDateTime;

public class ReviewResponse {

    public record WriteDTO(
            Long reviewId,
            LocalDateTime createdAt
    ) {}
}
