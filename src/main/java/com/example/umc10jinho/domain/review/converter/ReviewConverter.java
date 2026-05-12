package com.example.umc10jinho.domain.review.converter;

import com.example.umc10jinho.domain.review.dto.ReviewResDTO;
import com.example.umc10jinho.domain.review.entity.Review;

public class ReviewConverter {

    public static ReviewResDTO.WriteReviewResponse toWriteReviewResponse(Review review) {
        return new ReviewResDTO.WriteReviewResponse(
                review.getId(),
                review.getMarket().getName(),
                review.getBody(),
                review.getScore(),
                review.getCreatedAt()
        );
    }
}
