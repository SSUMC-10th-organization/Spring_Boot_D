package com.example.umc_week4.domain.review.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ReviewReqDTO {

    // 리뷰 작성
    public record CreateReview(
            @JsonProperty("mission_id")
            Long missionId,

            @JsonProperty("store_id")
            Long storeId,

            @JsonProperty("store_name")
            String storeName,

            Integer star,
            String content,

            @JsonProperty("photo_url")
            String photoUrl
    ) {
    }
}