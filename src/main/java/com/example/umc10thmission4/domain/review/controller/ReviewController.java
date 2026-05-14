package com.example.umc10thmission4.domain.review.controller;

import com.example.umc10thmission4.domain.review.dto.ReviewReqDTO;
import com.example.umc10thmission4.domain.review.dto.ReviewResDTO;
import com.example.umc10thmission4.domain.review.exception.code.ReviewSuccessCode;
import com.example.umc10thmission4.domain.review.service.ReviewService;
import com.example.umc10thmission4.global.apiPayload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping("/reviews")
    public ApiResponse<ReviewResDTO.CreateResultDTO> createReview(
            @RequestBody ReviewReqDTO.CreatDTO request
    ) {
        return ApiResponse.onSuccess(ReviewSuccessCode.OK, reviewService.createReview(request));
    }
}
