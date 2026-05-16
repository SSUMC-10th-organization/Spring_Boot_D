package com.example.umc10th.domain.review.repository;

import com.example.umc10th.domain.review.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
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

    // 내가 작성한 리뷰 - ID 첫 조회

    @Query("""

            select r

            from Review r

            join fetch r.member m

            join fetch r.store s

            where r.member.id = :memberId

              and r.deletedAt is null

            order by r.id desc

            """)
    Slice<Review> findMyReviewsOrderByIdDesc(

            @Param("memberId") Long memberId,

            Pageable pageable

    );

    // 내가 작성한 리뷰 - ID 커서 조회

    @Query("""

            select r

            from Review r

            join fetch r.member m

            join fetch r.store s

            where r.member.id = :memberId

              and r.deletedAt is null

              and r.id < :reviewId

            order by r.id desc

            """)

    Slice<Review> findMyReviewsByIdCursor(

            @Param("memberId") Long memberId,

            @Param("reviewId") Long reviewId,

            Pageable pageable

    );

    // 내가 작성한 리뷰 - 별점 첫 조회

    @Query("""

            select r

            from Review r

            join fetch r.member m

            join fetch r.store s

            where r.member.id = :memberId

              and r.deletedAt is null

            order by r.rating desc, r.id desc

            """)

    Slice<Review> findMyReviewsOrderByRatingDesc(

            @Param("memberId") Long memberId,

            Pageable pageable

    );

    // 내가 작성한 리뷰 - 별점 커서 조회

    @Query("""

            select r

            from Review r

            join fetch r.member m

            join fetch r.store s

            where r.member.id = :memberId

              and r.deletedAt is null

              and (

                    r.rating < :rating

                    or (r.rating = :rating and r.id < :reviewId)

                  )

            order by r.rating desc, r.id desc

            """)

    Slice<Review> findMyReviewsByRatingCursor(

            @Param("memberId") Long memberId,

            @Param("rating") Integer rating,

            @Param("reviewId") Long reviewId,

            Pageable pageable

    );
}