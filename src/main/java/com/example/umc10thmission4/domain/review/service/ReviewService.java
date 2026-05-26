package com.example.umc10thmission4.domain.review.service;

import com.example.umc10thmission4.domain.review.entity.Review;
import com.example.umc10thmission4.domain.review.repository.ReviewRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class ReviewService {

    private final ReviewRepository reviewRepository;

    // 텍스트 리뷰 생성 로직
    public void createReview(String content, Integer score) {
        Review review = Review.builder()
                .reviewText(content)
                .ratings(score)
                .build();
        reviewRepository.save(review);
    }
}
