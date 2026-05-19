package com.example.umc10jinho.domain.review.converter;

import com.example.umc10jinho.domain.review.dto.ReviewResDTO;
import com.example.umc10jinho.domain.review.entity.Review;

import java.util.List;

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

    public static ReviewResDTO.MyReviewPreview toMyReviewPreview(Review review) {
        return new ReviewResDTO.MyReviewPreview(
                review.getId(),
                review.getMarket().getName(),
                review.getBody(),
                review.getScore(),
                review.getCreatedAt()
        );
    }

    public static ReviewResDTO.MyReviewListResponse toMyReviewListResponse(List<Review> reviews, int size) {
        boolean hasNext = reviews.size() > size;
        List<Review> content = hasNext ? reviews.subList(0, size) : reviews;

        List<ReviewResDTO.MyReviewPreview> previews = content.stream()
                .map(ReviewConverter::toMyReviewPreview)
                .toList();

        Long lastId = content.isEmpty() ? null : content.getLast().getId();
        Float lastScore = content.isEmpty() ? null : content.getLast().getScore();

        return new ReviewResDTO.MyReviewListResponse(previews, hasNext, lastId, lastScore);
    }
}
