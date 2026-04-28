package com.example.umc10th.domain.review.controller;

import com.example.umc10th.domain.review.dto.ReviewReqDTO;
import com.example.umc10th.domain.review.dto.ReviewResDTO;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.GeneralSuccessCode;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/users")
@RestController
public class ReviewController {

    @PostMapping("/reviews")
    public ApiResponse<String> createReview(
            @RequestBody Long uerId,
            @RequestBody ReviewReqDTO.createReview request
            ) {
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, "성공");
    }
}
