package com.example.umc_week4.domain.review.controller;

import com.example.umc_week4.domain.review.dto.ReviewReqDTO;
import com.example.umc_week4.domain.review.dto.ReviewResDTO;
import com.example.umc_week4.domain.review.exception.code.ReviewSuccessCode;
import com.example.umc_week4.global.apiPayload.ApiResponse;
import com.example.umc_week4.global.apiPayload.code.BaseSuccessCode;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class ReviewController {

    // 리뷰 작성
    @PostMapping("/users/me/reviews")
    public ApiResponse<ReviewResDTO.CreateReviewResult> createReview(
            @RequestHeader("Authorization") String authorization,
            @RequestBody ReviewReqDTO.CreateReview request
    ) {
        BaseSuccessCode code = ReviewSuccessCode.CREATE_REVIEW_SUCCESS;

        ReviewResDTO.CreateReviewResult result = new ReviewResDTO.CreateReviewResult(
                null,
                request.missionId(),
                request.storeId(),
                request.storeName(),
                request.star(),
                request.content(),
                request.photoUrl()
        );

        return ApiResponse.onSuccess(code, result);
    }
}