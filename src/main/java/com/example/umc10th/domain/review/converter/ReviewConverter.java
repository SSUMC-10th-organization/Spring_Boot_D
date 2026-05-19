package com.example.umc10th.domain.review.converter;

import com.example.umc10th.domain.review.dto.ReviewResponse;
import com.example.umc10th.domain.review.entity.Review;

import java.util.List;

public class ReviewConverter {

    // 1. 단건 Entity -> ReviewDTO 변환
    public static ReviewResponse.ReviewDTO toReviewDTO(Review review) {
        return ReviewResponse.ReviewDTO.builder()
                .reviewId(review.getId())
                .storeName(review.getStore().getName())
                .content(review.getContent())
                .star(review.getStar())
                .createdAt(review.getCreatedAt())
                .build();
    }

    // 2. 리뷰 리스트와 페이징 정보를 받아 최종 응답 DTO로 포장
    public static ReviewResponse.ReviewPreviewListDTO toReviewPreviewListDTO(
            List<ReviewResponse.ReviewDTO> reviewList,
            Boolean hasNext,
            String nextCursor
    ) {
        return ReviewResponse.ReviewPreviewListDTO.builder()
                .reviewList(reviewList)
                .listSize(reviewList.size())
                .hasNext(hasNext)
                .nextCursor(nextCursor)
                .build();
    }
}
