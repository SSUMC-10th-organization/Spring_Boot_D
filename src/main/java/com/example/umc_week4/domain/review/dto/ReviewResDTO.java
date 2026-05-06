package com.example.umc_week4.domain.review.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ReviewResDTO {

    public record CreateReviewResult(
            @JsonProperty("review_id")
            Long reviewId,

            @JsonProperty("store_id")
            Long storeId,

            String body,

            Float score
    ) {
    }
}