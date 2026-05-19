package com.example.umc10thmission4.domain.review.service;

import com.example.umc10thmission4.domain.review.converter.ReviewConverter;
import com.example.umc10thmission4.domain.review.dto.ReviewReqDTO;
import com.example.umc10thmission4.domain.review.dto.ReviewResDTO;
import com.example.umc10thmission4.domain.review.entity.Review;
import com.example.umc10thmission4.domain.review.repository.ReviewRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class ReviewService {

    private final ReviewRepository reviewRepository;

    // 텍스트 리뷰 생성 로직
    public void createReview(ReviewReqDTO.CreatDTO request) {
        Review review = Review.builder()
                .reviewText(request.content())
                .ratings(request.rating().intValue())  // Float → Integer 변환
                .build();
        reviewRepository.save(review);
    }

    public ReviewResDTO.Pagination<ReviewResDTO.GetReview> getMyReviews(
            ReviewReqDTO.GetMyReviewsDTO dto
    ) {
        PageRequest pageRequest = PageRequest.of(0, dto.pageSize());

        Slice<Review> reviewList;
        String nextCursor;

        // 커서가 있는 경우
        if (!dto.cursor().equals("-1")) {

            String[] cursorSplit = dto.cursor().split(":");

            switch (dto.query().toLowerCase()) {

                // ID순
                case "id":
                    Long idCursor = Long.parseLong(cursorSplit[1]);
                    reviewList = reviewRepository
                            .findByMember_IdAndIdLessThanOrderByIdDesc(
                                    dto.memberId(), idCursor, pageRequest);
                    break;

                // 별점순
                case "star":
                    Integer starCursor = Integer.parseInt(cursorSplit[1]);  // Float → Integer
                    Long starIdCursor = Long.parseLong(cursorSplit[3]);
                    reviewList = reviewRepository
                            .findByMember_IdAndStarCursor(
                                    dto.memberId(), starCursor, starIdCursor, pageRequest);
                    break;

                default:
                    throw new IllegalArgumentException("query는 id 또는 star만 가능합니다.");
            }

        } else {
            // 커서 없이 조회
            if ("star".equals(dto.query().toLowerCase())) {
                reviewList = reviewRepository
                        .findByMember_IdOrderByRatingsDescIdDesc(dto.memberId(), pageRequest);
            } else {
                reviewList = reviewRepository
                        .findByMember_IdOrderByIdDesc(dto.memberId(), pageRequest);
            }
        }

        // 다음 커서 계산
        Review lastReview = reviewList.getContent().getLast();
        if ("star".equals(dto.query().toLowerCase())) {
            nextCursor = "star:" + lastReview.getRatings() + ":id:" + lastReview.getId();
        } else {
            nextCursor = "id:" + lastReview.getId();
        }

        return ReviewConverter.toPagination(
                reviewList.map(ReviewConverter::toGetReview).toList(),
                reviewList.hasNext(),
                nextCursor,
                reviewList.getSize()
        );
    }
}
