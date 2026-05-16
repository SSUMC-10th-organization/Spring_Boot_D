package com.example.umc10th.domain.review.controller;

import com.example.umc10th.domain.review.dto.ReviewReqDTO;
import com.example.umc10th.domain.review.dto.ReviewResDTO;
import com.example.umc10th.domain.review.exception.code.ReviewSuccessCode;
import com.example.umc10th.domain.review.service.ReviewService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.BaseSuccesscode;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping("/stores/{storeId}/reviews")
    public ApiResponse<ReviewResDTO.StoreReviewListResponse> getStoreReviews(
            @PathVariable Long storeId,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size
    ) {
        return ApiResponse.onSuccess(reviewService.getStoreReviews(storeId, page, size));
    }

    @PostMapping("/reviews/{reviewId}/reply")
    public ApiResponse<ReviewResDTO.CreateReplyResponse> createReply(
            @PathVariable Long reviewId,
            @RequestBody @Valid ReviewReqDTO.CreateReplyRequest request
    ) {
        return ApiResponse.onSuccess(reviewService.createReply(reviewId, request));
    }

    // 내가 작성한 리뷰 조회

    @PostMapping("/api/v1/members/reviews")
    public ApiResponse<ReviewResDTO.CursorPagination<ReviewResDTO.MyReviewDTO>> getMyReviews(
            @RequestBody @Valid ReviewReqDTO.MyReviewCursorRequest request
    ) {
        BaseSuccesscode code = ReviewSuccessCode.OK;
        return ApiResponse.onSuccess(code, reviewService.getMyReviews(request));

    }
}
