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
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;
    private final MarketRepository marketRepository;

    public Review writeReview(Long memberId, ReviewReqDTO.WriteReviewRequest request) {
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

    @Transactional(readOnly = true)
    public List<Review> getMyReviewsById(Long memberId, Long lastId, int size) {
        memberRepository.findById(memberId)
                .orElseThrow(() -> new ProjectException(GeneralErrorCode.NOT_FOUND));

        PageRequest pageable = PageRequest.of(0, size + 1);

        if (lastId == null) {
            return reviewRepository.findByMemberIdOrderById(memberId, pageable);
        }
        return reviewRepository.findByMemberIdAndIdAfterOrderById(memberId, lastId, pageable);
    }

    @Transactional(readOnly = true)
    public List<Review> getMyReviewsByScore(Long memberId, Float lastScore, Long lastId, int size) {
        memberRepository.findById(memberId)
                .orElseThrow(() -> new ProjectException(GeneralErrorCode.NOT_FOUND));

        PageRequest pageable = PageRequest.of(0, size + 1);

        if (lastScore == null) {
            return reviewRepository.findByMemberIdOrderByScore(memberId, pageable);
        }
        return reviewRepository.findByMemberIdAfterScoreOrderByScore(memberId, lastScore, lastId, pageable);
    }
}
