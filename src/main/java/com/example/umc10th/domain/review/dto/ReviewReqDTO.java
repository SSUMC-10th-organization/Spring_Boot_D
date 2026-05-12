package com.example.umc10th.domain.review.dto;

import jakarta.validation.constraints.NotBlank;

public class ReviewReqDTO {

    public record CreateReplyRequest(
            @NotBlank(message = "답글 내용은 필수입니다.")
            String content
    ) {
    }
}
