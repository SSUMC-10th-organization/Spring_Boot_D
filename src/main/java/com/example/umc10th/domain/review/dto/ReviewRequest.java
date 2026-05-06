package com.example.umc10th.domain.review.dto;

public class ReviewRequest {

    public record WriteDTO(
            Long memberId,
            String content,
            Float star
    ) {}
}
