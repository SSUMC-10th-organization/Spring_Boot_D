package com.example.umc_week4.domain.review.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ReviewReqDTO {

    public record CreateReview(
            @JsonProperty("store_id")
            Long storeId,

            String body,

            Float score
    ) {
    }
}