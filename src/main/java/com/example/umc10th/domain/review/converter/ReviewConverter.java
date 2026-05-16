package com.example.umc10th.domain.review.converter;

import com.example.umc10th.domain.review.dto.ReviewResDTO;
import com.example.umc10th.domain.review.entity.Reply;
import com.example.umc10th.domain.review.entity.Review;
import org.springframework.data.domain.Page;

import java.util.List;

public class ReviewConverter {

    public static ReviewResDTO.StoreReviewDTO toStoreReviewDTO(Review review) {
        List<String> photoUrlList = review.getReviewPhotoList()
                .stream()
                .map(photo -> photo.getImageUrl())
                .toList();

        return ReviewResDTO.StoreReviewDTO.builder()
                .reviewId(review.getId())
                .memberId(review.getMember().getId())
                .nickname(review.getMember().getNickname())
                .rating(review.getRating())
                .content(review.getContent())
                .createdAt(review.getCreatedAt())
                .photoUrlList(photoUrlList)
                .reply(toReplyDTO(review.getReply()))
                .build();
    }

    public static ReviewResDTO.ReplyDTO toReplyDTO(Reply reply) {
        if (reply == null) {
            return null;
        }

        return ReviewResDTO.ReplyDTO.builder()
                .replyId(reply.getId())
                .content(reply.getContent())
                .createdAt(reply.getCreatedAt())
                .build();
    }

    public static ReviewResDTO.StoreReviewListResponse toStoreReviewListResponse(Page<Review> page) {
        List<ReviewResDTO.StoreReviewDTO> reviewList = page.getContent()
                .stream()
                .map(ReviewConverter::toStoreReviewDTO)
                .toList();

        return ReviewResDTO.StoreReviewListResponse.builder()
                .reviewList(reviewList)
                .page(page.getNumber())
                .size(page.getSize())
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .isFirst(page.isFirst())
                .isLast(page.isLast())
                .build();
    }

    public static ReviewResDTO.CreateReplyResponse toCreateReplyResponse(Reply reply) {
        return ReviewResDTO.CreateReplyResponse.builder()
                .replyId(reply.getId())
                .reviewId(reply.getReview().getId())
                .content(reply.getContent())
                .build();
    }
    // 내가 작성한 리뷰 조회 DTO 변환 - 사진 제외
    public static ReviewResDTO.MyReviewDTO toMyReviewDTO(Review review) {
        return ReviewResDTO.MyReviewDTO.builder()
                .reviewId(review.getId())
                .storeId(review.getStore().getId())
                .storeName(review.getStore().getName())
                .nickname(review.getMember().getNickname())
                .rating(review.getRating())
                .content(review.getContent())
                .createdAt(review.getCreatedAt())
                .reply(toReplyDTO(review.getReply()))
                .build();
    }

    // 커서 기반 페이지네이션 응답 생성
    public static <T> ReviewResDTO.CursorPagination<T> toCursorPagination(
            List<T> data,
            Boolean hasNext,
            String nextCursor,
            Integer pageSize
    ) {
        return ReviewResDTO.CursorPagination.<T>builder()
                .data(data)
                .hasNext(hasNext)
                .nextCursor(nextCursor)
                .pageSize(pageSize)
                .build();
    }
}
