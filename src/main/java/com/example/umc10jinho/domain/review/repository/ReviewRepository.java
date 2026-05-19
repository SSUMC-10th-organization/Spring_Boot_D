package com.example.umc10jinho.domain.review.repository;

import com.example.umc10jinho.domain.review.entity.Review;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    @Query("SELECT r FROM Review r WHERE r.member.id = :memberId ORDER BY r.createdAt DESC")
    List<Review> findReviewsByMemberId(@Param("memberId") Long memberId);

    // 커서 기반 - ID 순 (첫 페이지)
    @Query("SELECT r FROM Review r WHERE r.member.id = :memberId ORDER BY r.id ASC")
    List<Review> findByMemberIdOrderById(
            @Param("memberId") Long memberId,
            Pageable pageable
    );

    // 커서 기반 - ID 순 (이후 페이지)
    @Query("SELECT r FROM Review r WHERE r.member.id = :memberId AND r.id > :lastId ORDER BY r.id ASC")
    List<Review> findByMemberIdAndIdAfterOrderById(
            @Param("memberId") Long memberId,
            @Param("lastId") Long lastId,
            Pageable pageable
    );

    // 커서 기반 - 별점 순 (첫 페이지, 별점 내림차순 -> ID 오름차순)
    @Query("SELECT r FROM Review r WHERE r.member.id = :memberId ORDER BY r.score DESC, r.id ASC")
    List<Review> findByMemberIdOrderByScore(
            @Param("memberId") Long memberId,
            Pageable pageable
    );

    // 커서 기반 - 별점 순 (이후 페이지)
    @Query("SELECT r FROM Review r WHERE r.member.id = :memberId AND (r.score < :lastScore OR (r.score = :lastScore AND r.id > :lastId)) ORDER BY r.score DESC, r.id ASC")
    List<Review> findByMemberIdAfterScoreOrderByScore(
            @Param("memberId") Long memberId,
            @Param("lastScore") Float lastScore,
            @Param("lastId") Long lastId,
            Pageable pageable
    );
}
