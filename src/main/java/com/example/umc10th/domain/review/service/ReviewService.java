package com.example.umc10th.domain.review.service;

import com.example.umc10th.domain.review.converter.ReviewConverter;
import com.example.umc10th.domain.review.dto.ReviewReqDTO;
import com.example.umc10th.domain.review.dto.ReviewResDTO;
import com.example.umc10th.domain.review.entity.Reply;
import com.example.umc10th.domain.review.entity.Review;
import com.example.umc10th.domain.review.exception.ReviewException;
import com.example.umc10th.domain.review.exception.code.ReviewErrorCode;
import com.example.umc10th.domain.review.repository.ReplyRepository;
import com.example.umc10th.domain.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    // 내가 작성한 리뷰 조회 - 커서 기반 페이지네이션
    @Transactional(readOnly = true)
    public ReviewResDTO.CursorPagination<ReviewResDTO.MyReviewDTO> getMyReviews(
            ReviewReqDTO.MyReviewCursorRequest request
    ) {
        PageRequest pageRequest = PageRequest.of(0, request.pageSize());

        Slice<Review> reviewSlice;

        if ("-1".equals(request.cursor())) {
            if ("id".equals(request.query())) {
                reviewSlice = reviewRepository.findMyReviewsOrderByIdDesc(
                        request.memberId(),
                        pageRequest
                );
            } else if ("rating".equals(request.query())) {
                reviewSlice = reviewRepository.findMyReviewsOrderByRatingDesc(
                        request.memberId(),
                        pageRequest
                );
            } else {
                throw new ReviewException(ReviewErrorCode.QUERY_NOT_VALID);
            }
        } else {
            String[] cursorSplit = request.cursor().split(":");

            if (cursorSplit.length != 2) {
                throw new ReviewException(ReviewErrorCode.QUERY_NOT_VALID);
            }

            if ("id".equals(request.query())) {
                Long reviewIdCursor = Long.parseLong(cursorSplit[1]);

                reviewSlice = reviewRepository.findMyReviewsByIdCursor(
                        request.memberId(),
                        reviewIdCursor,
                        pageRequest
                );
            } else if ("rating".equals(request.query())) {
                Integer ratingCursor = Integer.parseInt(cursorSplit[0]);
                Long reviewIdCursor = Long.parseLong(cursorSplit[1]);

                reviewSlice = reviewRepository.findMyReviewsByRatingCursor(
                        request.memberId(),
                        ratingCursor,
                        reviewIdCursor,
                        pageRequest
                );
            } else {
                throw new ReviewException(ReviewErrorCode.QUERY_NOT_VALID);
            }
        }

        String nextCursor = null;

        if (!reviewSlice.getContent().isEmpty()) {
            List<Review> content = reviewSlice.getContent();
            Review lastReview = content.get(content.size() - 1);

            if ("rating".equals(request.query())) {
                nextCursor = lastReview.getRating() + ":" + lastReview.getId();
            } else {
                nextCursor = lastReview.getId() + ":" + lastReview.getId();
            }
        }

        return ReviewConverter.toCursorPagination(
                reviewSlice.getContent()
                        .stream()
                        .map(ReviewConverter::toMyReviewDTO)
                        .toList(),
                reviewSlice.hasNext(),
                nextCursor,
                reviewSlice.getSize()
        );
    }
}