package com.example.umc_week4.domain.review.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ReviewReqDTO {

    public record CreateReview(
            @NotNull(message = "가게 ID는 필수입니다.")
            @JsonProperty("store_id")
            Long storeId,

            @NotBlank(message = "리뷰 내용은 필수입니다.")
            String body,

            @NotNull(message = "별점은 필수입니다.")
            @DecimalMin(value = "0.0", message = "별점은 0 이상이어야 합니다.")
            @DecimalMax(value = "5.0", message = "별점은 5 이하이어야 합니다.")
            Float score
    ) {
    }

    public record GetMyReviews(
            @NotNull(message = "사용자 ID는 필수입니다.")
            @JsonProperty("member_id")
            Long memberId,

            @NotNull(message = "페이지 크기는 필수입니다.")
            @Min(value = 1, message = "페이지 크기는 1 이상이어야 합니다.")
            Integer pageSize,

            @NotBlank(message = "커서는 필수입니다.")
            String cursor,

            @NotBlank(message = "조회 기준은 필수입니다.")
            String query
    ) {
    }
}
