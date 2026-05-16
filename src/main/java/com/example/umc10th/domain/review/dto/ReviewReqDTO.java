package com.example.umc10th.domain.review.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class ReviewReqDTO {

    public record CreateReplyRequest(
            @NotBlank(message = "답글 내용은 필수입니다.")
            String content
    ) {}

    // 내가 작성한 리뷰 조회 - 커서 기반 페이지네이션

    public record MyReviewCursorRequest(

            @NotNull(message = "사용자 ID는 필수입니다.")
            Long memberId,
            @NotNull(message = "페이지 크기는 필수입니다.")
            @Min(value = 1, message = "페이지 크기는 1 이상이어야 합니다.")
            Integer pageSize,
            @NotBlank(message = "커서는 필수입니다. 첫 조회라면 -1을 넣어주세요.")
            String cursor,
            @NotBlank(message = "조회 기준은 필수입니다.")
            @Pattern(regexp = "id|rating", message = "조회 기준은 id 또는 rating만 가능합니다.")
            String query
    ) {}
}
