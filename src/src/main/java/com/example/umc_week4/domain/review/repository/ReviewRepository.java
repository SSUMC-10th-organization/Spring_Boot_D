package com.example.umc_week4.domain.review.repository;

import com.example.umc_week4.domain.review.entity.Review;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    Slice<Review> findByMember_IdOrderByIdDesc(Long memberId, Pageable pageable);

    Slice<Review> findByMember_IdAndIdLessThanOrderByIdDesc(Long memberId, Long idCursor, Pageable pageable);

    @Query("SELECT r FROM Review r " +
            "WHERE r.member.id = :memberId " +
            "ORDER BY r.score DESC, r.id DESC")
    Slice<Review> findByMemberIdOrderByScoreDescIdDesc(
            @Param("memberId") Long memberId,
            Pageable pageable
    );

    @Query("SELECT r FROM Review r " +
            "WHERE r.member.id = :memberId " +
            "AND (r.score < :scoreCursor OR (r.score = :scoreCursor AND r.id < :idCursor)) " +
            "ORDER BY r.score DESC, r.id DESC")
    Slice<Review> findByMemberIdAndScoreCursor(
            @Param("memberId") Long memberId,
            @Param("scoreCursor") Float scoreCursor,
            @Param("idCursor") Long idCursor,
            Pageable pageable
    );
}
