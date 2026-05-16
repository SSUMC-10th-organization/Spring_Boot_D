package com.example.umc10th.domain.review.repository;

import com.example.umc10th.domain.review.entity.Review;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReviewRepository extends JpaRepository<Review, Long> {


    // 1. 첫 페이지 조회 (커서 없음)
    @Query("SELECT r FROM Review r JOIN FETCH r.store WHERE r.member.id = :memberId ORDER BY r.star DESC, r.id DESC")
    Slice<Review> findFirstPageByMemberIdOrderByStarDesc(@Param("memberId") Long memberId, Pageable pageable);

    // 2. 다음 페이지 조회 (커서 있음: 별점 + ID 조합)
    @Query("SELECT r FROM Review r JOIN FETCH r.store WHERE r.member.id = :memberId " +
            "AND (r.star < :cursorStar OR (r.star = :cursorStar AND r.id < :cursorId)) " +
            "ORDER BY r.star DESC, r.id DESC")
    Slice<Review> findNextPageByMemberIdOrderByStarDesc(
            @Param("memberId") Long memberId,
            @Param("cursorStar") Float cursorStar,
            @Param("cursorId") Long cursorId,
            Pageable pageable);
}
