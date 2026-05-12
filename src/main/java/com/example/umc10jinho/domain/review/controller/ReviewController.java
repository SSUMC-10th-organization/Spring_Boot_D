package com.example.umc10jinho.domain.review.controller;

import com.example.umc10jinho.domain.review.converter.ReviewConverter;
import com.example.umc10jinho.domain.review.dto.ReviewReqDTO;
import com.example.umc10jinho.domain.review.dto.ReviewResDTO;
import com.example.umc10jinho.domain.review.entity.Review;
import com.example.umc10jinho.domain.review.exception.code.ReviewSuccessCode;
import com.example.umc10jinho.domain.review.service.ReviewService;
import com.example.umc10jinho.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
@Tag(name = "리뷰 API")
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping
    @Operation(summary = "리뷰 작성", description = "가게에 리뷰를 작성합니다. (사진 제외)")
    public ApiResponse<ReviewResDTO.WriteReviewResponse> writeReview(
            @RequestParam Long memberId,
            @RequestBody ReviewReqDTO.WriteReviewRequest request
    ) {
        Review review = reviewService.writeReview(memberId, request);
        return ApiResponse.onSuccess(ReviewSuccessCode.WRITE_REVIEW, ReviewConverter.toWriteReviewResponse(review));
    }
}
