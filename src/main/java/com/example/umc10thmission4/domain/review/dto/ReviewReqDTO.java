package com.example.umc10thmission4.domain.review.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.util.List;

public class ReviewReqDTO {
    //리뷰 작성
    @Builder
    public record CreatDTO(
            Long storeId,
            Float rating,
            String content,
            List<String> reviewImages
    ){}

    public record GetMyReviewsDTO(
            @NotNull(message = "사용자 ID는 필수입니다.")
            Long memberId,
            @NotNull(message = "페이지 크기는 필수입니다.")
            Integer pageSize,
            String cursor,  // 처음 요청시 "-1"
            String query    // "id" 또는 "star"
    ) {}
}
