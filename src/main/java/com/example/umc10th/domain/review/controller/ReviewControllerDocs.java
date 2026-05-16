package com.example.umc10th.domain.review.controller;

import com.example.umc10th.domain.review.dto.ReviewRequest;
import com.example.umc10th.domain.review.dto.ReviewResponse;
import com.example.umc10th.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public interface ReviewControllerDocs {

    @Operation(
            summary = "리뷰 작성 by 매튜/진현준",
            description = "리뷰 작성 API입니다."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "실패")
    })
    @PostMapping
    ResponseEntity<ReviewResponse.WriteDTO> writeReview(
            @PathVariable("storeId") Long storeId,
            @RequestBody ReviewRequest.WriteDTO request);


    @Operation(
            summary = "리뷰 조회 by 매튜/진현준",
            description = "리뷰 조회 API입니다."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "실패")
    })
    @GetMapping
    ApiResponse<ReviewResponse.ReviewPreviewListDTO> getMyReviews(
            @PathVariable("memberId") Long memberId,
            @RequestParam(name = "cursor", defaultValue = "-1") String cursor,
            @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize
    );
}
