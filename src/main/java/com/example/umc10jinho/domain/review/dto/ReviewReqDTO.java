package com.example.umc10jinho.domain.review.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ReviewReqDTO {

    public record WriteReviewRequest(
            @NotNull(message = "가게 ID는 필수입니다.")
            Long marketId,

            @NotBlank(message = "리뷰 내용은 필수입니다.")
            String body,

            @NotNull(message = "평점은 필수입니다.")
            @DecimalMin(value = "0.0", message = "평점은 0.0 이상이어야 합니다.")
            @DecimalMax(value = "5.0", message = "평점은 5.0 이하여야 합니다.")
            Float score
    ) {
    }
}
