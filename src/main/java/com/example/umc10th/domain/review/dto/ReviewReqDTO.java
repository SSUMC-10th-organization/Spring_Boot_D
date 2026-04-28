package com.example.umc10th.domain.review.dto;

import lombok.Builder;

public class ReviewReqDTO {

    @Builder
    public record createReview(
            Long storeId,
            String content,
            Float rating,
            String image // 이미지 url
    ) {}
}
