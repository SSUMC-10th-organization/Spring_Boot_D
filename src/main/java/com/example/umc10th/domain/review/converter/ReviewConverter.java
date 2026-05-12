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
}
