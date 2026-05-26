package com.example.umc_week4.domain.review.service;

import com.example.umc_week4.domain.member.entity.Member;
import com.example.umc_week4.domain.member.exception.MemberException;
import com.example.umc_week4.domain.member.exception.code.MemberErrorCode;
import com.example.umc_week4.domain.member.repository.MemberRepository;
import com.example.umc_week4.domain.mission.entity.Store;
import com.example.umc_week4.domain.mission.exception.StoreException;
import com.example.umc_week4.domain.mission.exception.code.StoreErrorCode;
import com.example.umc_week4.domain.mission.repository.StoreRepository;
import com.example.umc_week4.domain.review.converter.ReviewConverter;
import com.example.umc_week4.domain.review.dto.ReviewReqDTO;
import com.example.umc_week4.domain.review.dto.ReviewResDTO;
import com.example.umc_week4.domain.review.entity.Review;
import com.example.umc_week4.domain.review.exception.ReviewException;
import com.example.umc_week4.domain.review.exception.code.ReviewErrorCode;
import com.example.umc_week4.domain.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;
    private final StoreRepository storeRepository;

    @Transactional
    public ReviewResDTO.CreateReviewResult createReview(
            String name,
            ReviewReqDTO.CreateReview request
    ) {
        // SQL의 SELECT id FROM member WHERE name = ? 부분에 해당
        // 단순 이름 조건 조회이므로 MemberRepository의 메서드 이름쿼리 사용
        Member member = memberRepository.findByNameAndDeletedAtIsNull(name)
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

        // 요청으로 들어온 store_id에 해당하는 가게 조회
        Store store = storeRepository.findById(request.storeId())
                .orElseThrow(() -> new StoreException(StoreErrorCode.NOT_FOUND));

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

    // 내가 생성한 리뷰들 조회하기 - 커서 기반 페이지네이션
    @Transactional(readOnly = true)
    public ReviewResDTO.CursorPagination<ReviewResDTO.MyReview> getMyReviews(
            ReviewReqDTO.GetMyReviews dto
    ) {
        PageRequest pageRequest = PageRequest.of(0, dto.pageSize());
        Slice<Review> reviewList;

        if (dto.cursor().equals("-1")) {
            reviewList = getFirstPage(dto.memberId(), dto.query(), pageRequest);
        } else {
            String[] cursorSplit = dto.cursor().split(":");
            reviewList = getNextPage(dto.memberId(), dto.query(), cursorSplit, pageRequest);
        }

        String nextCursor = makeNextCursor(dto.query(), reviewList);

        return ReviewConverter.toCursorPagination(
                reviewList.map(ReviewConverter::toMyReview).toList(),
                reviewList.hasNext(),
                nextCursor,
                reviewList.getSize()
        );
    }

    private Slice<Review> getFirstPage(Long memberId, String query, PageRequest pageRequest) {
        return switch (query.toLowerCase()) {
            case "id" -> reviewRepository.findByMember_IdOrderByIdDesc(memberId, pageRequest);
            case "star", "score" -> reviewRepository.findByMemberIdOrderByScoreDescIdDesc(memberId, pageRequest);
            default -> throw new ReviewException(ReviewErrorCode.QUERY_NOT_VALID);
        };
    }

    private Slice<Review> getNextPage(Long memberId, String query, String[] cursorSplit, PageRequest pageRequest) {
        try {
            return switch (query.toLowerCase()) {
                case "id" -> {
                    Long idCursor = Long.parseLong(cursorSplit[1]);
                    yield reviewRepository.findByMember_IdAndIdLessThanOrderByIdDesc(memberId, idCursor, pageRequest);
                }
                case "star", "score" -> {
                    Float scoreCursor = Float.parseFloat(cursorSplit[0]);
                    Long idCursor = Long.parseLong(cursorSplit[1]);
                    yield reviewRepository.findByMemberIdAndScoreCursor(memberId, scoreCursor, idCursor, pageRequest);
                }
                default -> throw new ReviewException(ReviewErrorCode.QUERY_NOT_VALID);
            };
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
            throw new ReviewException(ReviewErrorCode.CURSOR_NOT_VALID);
        }
    }

    private String makeNextCursor(String query, Slice<Review> reviewList) {
        if (!reviewList.hasContent()) {
            return null;
        }

        Review lastReview = reviewList.getContent().get(reviewList.getContent().size() - 1);

        return switch (query.toLowerCase()) {
            case "id" -> lastReview.getId() + ":" + lastReview.getId();
            case "star", "score" -> lastReview.getScore() + ":" + lastReview.getId();
            default -> throw new ReviewException(ReviewErrorCode.QUERY_NOT_VALID);
        };
    }
}
