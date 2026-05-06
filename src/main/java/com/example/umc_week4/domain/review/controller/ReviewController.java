package com.example.umc_week4.domain.review.controller;

import com.example.umc_week4.domain.review.dto.ReviewReqDTO;
import com.example.umc_week4.domain.review.dto.ReviewResDTO;
import com.example.umc_week4.domain.review.exception.code.ReviewSuccessCode;
import com.example.umc_week4.domain.review.service.ReviewService;
import com.example.umc_week4.global.apiPayload.ApiResponse;
import com.example.umc_week4.global.apiPayload.code.BaseSuccessCode;
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
            @RequestBody ReviewReqDTO.CreateReview request
    ) {
        BaseSuccessCode code = ReviewSuccessCode.CREATE_REVIEW_SUCCESS;
        ReviewResDTO.CreateReviewResult result = reviewService.createReview(name, request);

        return ApiResponse.onSuccess(code, result);
    }
}