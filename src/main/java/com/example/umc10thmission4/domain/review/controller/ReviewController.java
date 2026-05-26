package com.example.umc10thmission4.domain.review.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reviews")
public class ReviewController {

    /*private final ReviewService reviewService;

    @PostMapping("/reviews")
    public ApiResponse<ReviewResDTO.CreateResultDTO> createReview(
            @RequestBody ReviewReqDTO.CreatDTO request
    ) {
        return ApiResponse.onSuccess(ReviewSuccessCode.OK, reviewService.createReview(request));
    }*/
}
