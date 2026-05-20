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
    ) {}

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
    ) {}

    @Builder
    public record ReplyDTO(
            Long replyId,
            String content,
            LocalDateTime createdAt
    ) {}

    @Builder
    public record CreateReplyResponse(
            Long replyId,
            Long reviewId,
            String content
    ) {}

    // 내가 작성한 리뷰 조회 응답 - 사진 제외
    @Builder
    public record MyReviewDTO(
            Long reviewId,
            Long storeId,
            String storeName,
            String nickname,
            Integer rating,
            String content,
            LocalDateTime createdAt,
            ReplyDTO reply
    ) {}

    // 커서 기반 페이지네이션 응답 툴
    @Builder
    public record CursorPagination<T>(
            List<T> data,
            Boolean hasNext,
            String nextCursor,
            Integer pageSize
    ) {}
}
