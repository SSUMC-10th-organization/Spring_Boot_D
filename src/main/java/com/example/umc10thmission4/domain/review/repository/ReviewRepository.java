package com.example.umc10thmission4.domain.review.repository;

import com.example.umc10thmission4.domain.review.entity.Review;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    // ID순 - 커서 없이
    Slice<Review> findByMember_IdOrderByIdDesc(Long memberId, Pageable pageable);

    // ID순 - 커서 있을 때
    Slice<Review> findByMember_IdAndIdLessThanOrderByIdDesc(Long memberId, Long idCursor, Pageable pageable);

    // 별점순 - 커서 없이
    Slice<Review> findByMember_IdOrderByRatingsDescIdDesc(Long memberId, Pageable pageable);

    // 별점순 - 커서 있을 때 (복합 커서)
    @Query("SELECT r FROM Review r WHERE r.member.id = :memberId " +
            "AND (r.ratings < :ratings OR (r.ratings = :ratings AND r.id < :id)) " +
            "ORDER BY r.ratings DESC, r.id DESC")
    Slice<Review> findByMember_IdAndStarCursor(
            @Param("memberId") Long memberId,
            @Param("ratings") Integer ratings,
            @Param("id") Long id,
            Pageable pageable
    );
}
