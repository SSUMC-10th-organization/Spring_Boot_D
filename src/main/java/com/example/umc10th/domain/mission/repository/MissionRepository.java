package com.example.umc10th.domain.mission.repository;

import com.example.umc10th.domain.mission.entity.Mission;
import com.example.umc10th.domain.mission.entity.Store;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MissionRepository extends JpaRepository<Mission, Long> {

    @Query(
            value = """
                    select m
                    from Mission m
                    join fetch m.store s
                    where m.deletedAt is null
                      and m.id not in (
                        select mm.mission.id
                        from MemberMission mm
                        where mm.member.id = :memberId
                      )
                    order by m.createdAt desc
                    """,
            countQuery = """
                    select count(m)
                    from Mission m
                    where m.deletedAt is null
                      and m.id not in (
                        select mm.mission.id
                        from MemberMission mm
                        where mm.member.id = :memberId
                      )
                    """
    )
    Page<Mission> findHomeMissions(
            @Param("memberId") Long memberId,
            Pageable pageable
    );

    Page<Mission> findAllByStore_Id(Long storeId, Pageable pageable);

    Slice<Mission> findMissionByStore_IdOrderByIdDesc(
            Long storeId,
            Pageable pageable

    );

    Slice<Mission> findMissionByStore_IdAndIdLessThanOrderByIdDesc(
            Long storeId,
            Long id,
            Pageable pageable
    );
}