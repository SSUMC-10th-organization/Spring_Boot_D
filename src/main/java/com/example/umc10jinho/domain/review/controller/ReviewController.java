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
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
            @Valid @RequestBody ReviewReqDTO.WriteReviewRequest request
    ) {
        Review review = reviewService.writeReview(memberId, request);
        return ApiResponse.onSuccess(ReviewSuccessCode.WRITE_REVIEW, ReviewConverter.toWriteReviewResponse(review));
    }

    @GetMapping("/my")
    @Operation(
            summary = "내 리뷰 목록 조회",
            description = "커서 기반 페이지네이션으로 내 리뷰를 조회합니다. sortBy=ID이면 ID 오름차순, sortBy=SCORE이면 별점 내림차순으로 정렬됩니다."
    )
    public ApiResponse<ReviewResDTO.MyReviewListResponse> getMyReviews(
            @RequestParam Long memberId,
            @RequestParam(defaultValue = "ID") String sortBy,
            @RequestParam(required = false) Long lastId,
            @RequestParam(required = false) Float lastScore,
            @RequestParam(defaultValue = "10") int size
    ) {
        List<Review> reviews;
        if ("SCORE".equalsIgnoreCase(sortBy)) {
            reviews = reviewService.getMyReviewsByScore(memberId, lastScore, lastId, size);
        } else {
            reviews = reviewService.getMyReviewsById(memberId, lastId, size);
        }
        return ApiResponse.onSuccess(
                ReviewSuccessCode.GET_MY_REVIEWS,
                ReviewConverter.toMyReviewListResponse(reviews, size)
        );
    }
}
