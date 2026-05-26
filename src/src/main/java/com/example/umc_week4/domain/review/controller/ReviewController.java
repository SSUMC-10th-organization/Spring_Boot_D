package com.example.umc_week4.domain.review.controller;

import com.example.umc_week4.domain.review.dto.ReviewReqDTO;
import com.example.umc_week4.domain.review.dto.ReviewResDTO;
import com.example.umc_week4.domain.review.exception.code.ReviewSuccessCode;
import com.example.umc_week4.domain.review.service.ReviewService;
import com.example.umc_week4.global.apiPayload.ApiResponse;
import com.example.umc_week4.global.apiPayload.code.BaseSuccessCode;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping("/users/me/reviews")
    public ApiResponse<ReviewResDTO.CreateReviewResult> createReview(
            @RequestParam(defaultValue = "닉네임1234") String name,
            @RequestBody @Valid ReviewReqDTO.CreateReview request
    ) {
        BaseSuccessCode code = ReviewSuccessCode.CREATE_REVIEW_SUCCESS;
        ReviewResDTO.CreateReviewResult result = reviewService.createReview(name, request);

        return ApiResponse.onSuccess(code, result);
    }

    // 내가 생성한 리뷰들 조회하기
    @GetMapping("/users/me/reviews")
    public ApiResponse<ReviewResDTO.CursorPagination<ReviewResDTO.MyReview>> getMyReviews(
            @RequestBody @Valid ReviewReqDTO.GetMyReviews dto
    ) {
        BaseSuccessCode code = ReviewSuccessCode.OK;
        return ApiResponse.onSuccess(code, reviewService.getMyReviews(dto));
    }

    // Swagger에서 Request Body로 조회 테스트하기 좋은 POST 별칭
    @PostMapping("/users/me/reviews/search")
    public ApiResponse<ReviewResDTO.CursorPagination<ReviewResDTO.MyReview>> searchMyReviews(
            @RequestBody @Valid ReviewReqDTO.GetMyReviews dto
    ) {
        return getMyReviews(dto);
    }
}
