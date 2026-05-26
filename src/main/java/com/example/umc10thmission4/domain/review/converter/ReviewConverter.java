package com.example.umc10thmission4.domain.review.converter;

import com.example.umc10thmission4.domain.review.dto.ReviewResDTO;
import com.example.umc10thmission4.domain.review.entity.Review;

import java.util.List;

public class ReviewConverter {

    public static ReviewResDTO.GetReview toGetReview(Review review) {
        return ReviewResDTO.GetReview.builder()
                .reviewId(review.getId())
                .body(review.getReviewText())
                .ratings(review.getRatings())
                .createdAt(review.getCreatedAt().toString())
                .storeName(review.getStore().getStoreName())
                .build();
    }

    public static <T> ReviewResDTO.Pagination<T> toPagination(
            List<T> data,
            Boolean hasNext,
            String nextCursor,
            Integer pageSize
    ) {
        return ReviewResDTO.Pagination.<T>builder()
                .data(data)
                .hasNext(hasNext)
                .nextCursor(nextCursor)
                .pageSize(pageSize)
                .build();
    }
}
