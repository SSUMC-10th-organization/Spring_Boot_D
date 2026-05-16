package com.example.umc10th.domain.review.service;

import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.exception.MemberException;
import com.example.umc10th.domain.member.exception.code.MemberErrorCode;
import com.example.umc10th.domain.member.repository.MemberRepository;
import com.example.umc10th.domain.review.converter.ReviewConverter;
import com.example.umc10th.domain.review.dto.ReviewRequest;
import com.example.umc10th.domain.review.dto.ReviewResponse;
import com.example.umc10th.domain.review.entity.Review;
import com.example.umc10th.domain.review.exception.ReviewException;
import com.example.umc10th.domain.review.exception.code.ReviewErrorCode;
import com.example.umc10th.domain.review.repository.ReviewRepository;
import com.example.umc10th.domain.store.entity.Store;
import com.example.umc10th.domain.store.exception.code.StoreErrorCode;
import com.example.umc10th.domain.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;
    private final StoreRepository storeRepository;

    @Transactional
    public Review createReview(Long storeId, ReviewRequest.WriteDTO request) {

        Member member = memberRepository.findById(request.memberId())
                .orElseThrow(() -> new MemberException(MemberErrorCode.NOT_FOUND.getMessage()));

        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new SecurityException(StoreErrorCode.NOT_FOUND.getMessage()));

        Review review = Review.builder()
                .member(member)
                .store(store)
                .content(request.content())
                .star(request.star())
                .build();

        return reviewRepository.save(review);
    }

    @Transactional(readOnly = true)
    public ReviewResponse.ReviewPreviewListDTO getMyReviews(
            Long memberId, String cursor, Integer pageSize) {

        PageRequest pageRequest = PageRequest.of(0, pageSize);
        Slice<Review> reviewSlice;

        // 1. 첫 페이지 조회
        if (cursor == null || cursor.equals("-1")) {
            reviewSlice = reviewRepository.findFirstPageByMemberIdOrderByStarDesc(memberId, pageRequest);
        }
        // 2. 다음 페이지 조회 (커서 분리)
        else {
            String[] cursorSplit = cursor.split(":");
            Float cursorStar = Float.parseFloat(cursorSplit[0]);
            Long cursorId = Long.parseLong(cursorSplit[1]);

            reviewSlice = reviewRepository.findNextPageByMemberIdOrderByStarDesc(
                    memberId, cursorStar, cursorId, pageRequest);
        }

        // 3. 다음 커서 계산
        String nextCursor = "-1";
        if (reviewSlice.hasNext()) {
            Review lastReview = reviewSlice.getContent().getLast(); // Java 21 문법
            nextCursor = lastReview.getStar() + ":" + lastReview.getId();
        }

        // 4. Entity에서 DTO로 변환
        List<ReviewResponse.ReviewDTO> reviewDTOList = reviewSlice.getContent().stream()
                .map(ReviewConverter::toReviewDTO)
                .toList();

        // 5. 리뷰 전용 Pagination 컨터버 처리 후 반환
        return ReviewConverter.toReviewPreviewListDTO(
                reviewDTOList,
                reviewSlice.hasNext(),
                nextCursor
        );
    }
}
