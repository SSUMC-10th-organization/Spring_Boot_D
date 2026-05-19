package com.example.umc10th.domain.review.controller;

import com.example.umc10th.domain.review.dto.ReviewRequest;
import com.example.umc10th.domain.review.dto.ReviewResponse;
import com.example.umc10th.domain.review.entity.Review;
import com.example.umc10th.domain.review.exception.code.ReviewSuccessCode;
import com.example.umc10th.domain.review.service.ReviewService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/stores/{storeId}/reviews")
public class ReviewController implements ReviewControllerDocs{

    private final ReviewService reviewService;

    @PostMapping
    @Override
    public ResponseEntity<ReviewResponse.WriteDTO> writeReview(
            @PathVariable("storeId") Long storeId,
            @RequestBody ReviewRequest.WriteDTO request) {

        Review savedReview = reviewService.createReview(storeId, request);

        ReviewResponse.WriteDTO response = new ReviewResponse.WriteDTO(
                savedReview.getId(),
                savedReview.getCreatedAt()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    @Override
    public ApiResponse<ReviewResponse.ReviewPreviewListDTO> getMyReviews(
            @PathVariable("memberId") Long memberId,
            @RequestParam(name = "cursor", defaultValue = "-1") String cursor,
            @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize
    ) {

        // 완전히 리뷰 도메인 전용 DTO로 깔끔하게 응답
        ReviewResponse.ReviewPreviewListDTO response =
                reviewService.getMyReviews(memberId, cursor, pageSize);

        return ApiResponse.onSuccess(ReviewSuccessCode.OK, response);
    }
}