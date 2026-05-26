package com.example.umc_week4.domain.review.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

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

    @Builder
    public record MyReview(
            @JsonProperty("review_id")
            Long reviewId,

            @JsonProperty("store_id")
            Long storeId,

            @JsonProperty("store_name")
            String storeName,

            String body,

            Float score,

            @JsonProperty("created_at")
            LocalDateTime createdAt
    ){}

    @Builder
    public record CursorPagination<T>(
            List<T> data,
            Boolean hasNext,
            String nextCursor,
            Integer pageSize
    ){}
}
