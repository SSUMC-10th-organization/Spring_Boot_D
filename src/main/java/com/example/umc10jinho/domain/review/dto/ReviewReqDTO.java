package com.example.umc10jinho.domain.review.dto;

public class ReviewReqDTO {

    public record WriteReviewRequest(
            Long marketId,
            String body,
            Float score
    ) {
    }
}
