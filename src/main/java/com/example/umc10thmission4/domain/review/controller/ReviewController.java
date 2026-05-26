package com.example.umc10thmission4.domain.review.controller;

import com.example.umc10thmission4.domain.review.dto.ReviewReqDTO;
import com.example.umc10thmission4.domain.review.dto.ReviewResDTO;
import com.example.umc10thmission4.domain.review.service.ReviewService;
import com.example.umc10thmission4.global.apiPayload.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ReviewController {

    private final ReviewService reviewService;

    // 리뷰 생성
    @PostMapping("/v1/reviews")
    public ApiResponse<Void> createReview(
            @RequestBody ReviewReqDTO.CreatDTO request
    ) {
        reviewService.createReview(request);  // 반환값 사용 X
        return ApiResponse.onSuccess(null);
    }

    // 내가 작성한 리뷰 조회 (커서 기반 페이지네이션)
    @PostMapping("/v1/members/reviews")
    public ApiResponse<ReviewResDTO.Pagination<ReviewResDTO.GetReview>> getMyReviews(
            @RequestBody @Valid ReviewReqDTO.GetMyReviewsDTO dto
    ) {
        return ApiResponse.onSuccess(reviewService.getMyReviews(dto));
    }
}