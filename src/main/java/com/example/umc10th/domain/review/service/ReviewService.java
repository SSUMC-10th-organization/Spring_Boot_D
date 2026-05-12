package com.example.umc10th.domain.review.service;

import com.example.umc10th.domain.review.converter.ReviewConverter;
import com.example.umc10th.domain.review.dto.ReviewReqDTO;
import com.example.umc10th.domain.review.dto.ReviewResDTO;
import com.example.umc10th.domain.review.entity.Reply;
import com.example.umc10th.domain.review.entity.Review;
import com.example.umc10th.domain.review.repository.ReplyRepository;
import com.example.umc10th.domain.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final ReplyRepository replyRepository;

    public ReviewResDTO.StoreReviewListResponse getStoreReviews(
            Long storeId,
            Integer page,
            Integer size
    ) {
        Page<Review> reviewPage = reviewRepository.findStoreReviews(
                storeId,
                PageRequest.of(page, size)
        );

        return ReviewConverter.toStoreReviewListResponse(reviewPage);
    }

    @Transactional
    public ReviewResDTO.CreateReplyResponse createReply(
            Long reviewId,
            ReviewReqDTO.CreateReplyRequest request
    ) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 리뷰입니다."));

        if (replyRepository.existsByReviewId(reviewId)) {
            throw new IllegalArgumentException("이미 답글이 작성된 리뷰입니다.");
        }

        Reply reply = Reply.builder()
                .review(review)
                .content(request.content())
                .build();

        Reply savedReply = replyRepository.save(reply);

        return ReviewConverter.toCreateReplyResponse(savedReply);
    }
}