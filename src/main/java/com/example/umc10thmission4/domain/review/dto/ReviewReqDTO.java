package com.example.umc10thmission4.domain.review.dto;

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
}
