package com.example.umc10th.domain.review.dto;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

public class ReviewResDTO {

    @Builder
    public record StoreReviewListResponse(
            List<StoreReviewDTO> reviewList,
            Integer page,
            Integer size,
            Long totalElements,
            Integer totalPages,
            Boolean isFirst,
            Boolean isLast
    ) {
    }

    @Builder
    public record StoreReviewDTO(
            Long reviewId,
            Long memberId,
            String nickname,
            Integer rating,
            String content,
            LocalDateTime createdAt,
            List<String> photoUrlList,
            ReplyDTO reply
    ) {
    }

    @Builder
    public record ReplyDTO(
            Long replyId,
            String content,
            LocalDateTime createdAt
    ) {
    }

    @Builder
    public record CreateReplyResponse(
            Long replyId,
            Long reviewId,
            String content
    ) {
    }
}
