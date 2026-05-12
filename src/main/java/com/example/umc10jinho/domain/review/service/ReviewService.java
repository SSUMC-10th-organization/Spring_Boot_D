package com.example.umc10jinho.domain.review.service;

import com.example.umc10jinho.domain.market.entity.Market;
import com.example.umc10jinho.domain.market.repository.MarketRepository;
import com.example.umc10jinho.domain.member.entity.Member;
import com.example.umc10jinho.domain.member.repository.MemberRepository;
import com.example.umc10jinho.domain.review.dto.ReviewReqDTO;
import com.example.umc10jinho.domain.review.entity.Review;
import com.example.umc10jinho.domain.review.exception.ReviewException;
import com.example.umc10jinho.domain.review.exception.code.ReviewErrorCode;
import com.example.umc10jinho.domain.review.repository.ReviewRepository;
import com.example.umc10jinho.global.apiPayload.code.GeneralErrorCode;
import com.example.umc10jinho.global.apiPayload.exception.ProjectException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;
    private final MarketRepository marketRepository;

    public Review writeReview(Long memberId, ReviewReqDTO.WriteReviewRequest request) {
        if (request.score() < 0.0f || request.score() > 5.0f) {
            throw new ReviewException(ReviewErrorCode.INVALID_SCORE);
        }

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new ProjectException(GeneralErrorCode.NOT_FOUND));
        Market market = marketRepository.findById(request.marketId())
                .orElseThrow(() -> new ProjectException(GeneralErrorCode.NOT_FOUND));

        Review review = Review.builder()
                .body(request.body())
                .score(request.score())
                .member(member)
                .market(market)
                .build();

        return reviewRepository.save(review);
    }
}
