package com.example.umc10th.domain.review.repository;

import com.example.umc10th.domain.review.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    Long countByMemberIdAndDeletedAtIsNull(Long memberId);

    @Query(
            value = """
                    select r
                    from Review r
                    join fetch r.member m
                    where r.store.id = :storeId
                      and r.deletedAt is null
                    order by r.createdAt desc
                    """,
            countQuery = """
                    select count(r)
                    from Review r
                    where r.store.id = :storeId
                      and r.deletedAt is null
                    """
    )
    Page<Review> findStoreReviews(
            @Param("storeId") Long storeId,
            Pageable pageable
    );
}