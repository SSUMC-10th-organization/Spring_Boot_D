package com.example.umc_week4.domain.review.service;

import com.example.umc_week4.domain.member.entity.Member;
import com.example.umc_week4.domain.member.repository.MemberRepository;
import com.example.umc_week4.domain.mission.entity.Store;
import com.example.umc_week4.domain.mission.repository.StoreRepository;
import com.example.umc_week4.domain.review.dto.ReviewReqDTO;
import com.example.umc_week4.domain.review.dto.ReviewResDTO;
import com.example.umc_week4.domain.review.entity.Review;
import com.example.umc_week4.domain.review.repository.ReviewRepository;
import com.example.umc_week4.global.apiPayload.code.GeneralErrorCode;
import com.example.umc_week4.global.apiPayload.exception.ProjectException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;
    private final StoreRepository storeRepository;

    public ReviewResDTO.CreateReviewResult createReview(
            String name,
            ReviewReqDTO.CreateReview request
    ) {
        // SQL의 SELECT id FROM member WHERE name = ? 부분에 해당
        // 단순 이름 조건 조회이므로 MemberRepository의 메서드 이름쿼리 사용
        Member member = memberRepository.findByNameAndDeletedAtIsNull(name)
                .orElseThrow(() -> new ProjectException(GeneralErrorCode.NOT_FOUND));

        // 요청으로 들어온 store_id에 해당하는 가게 조회
        Store store = storeRepository.findById(request.storeId())
                .orElseThrow(() -> new ProjectException(GeneralErrorCode.NOT_FOUND));

        // INSERT INTO review (...) VALUES (...)에 들어갈 데이터를 엔티티로 생성
        Review review = Review.builder()
                .member(member)
                .store(store)
                .body(request.body())
                .score(request.score())
                .build();

        Review savedReview = reviewRepository.save(review);

        return new ReviewResDTO.CreateReviewResult(
                savedReview.getId(),
                store.getId(),
                savedReview.getBody(),
                savedReview.getScore()
        );
    }
}