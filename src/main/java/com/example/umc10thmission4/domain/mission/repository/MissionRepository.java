package com.example.umc10thmission4.domain.mission.repository;

import com.example.umc10thmission4.domain.member.entity.mapping.MemberMission;
import com.example.umc10thmission4.domain.mission.entity.Mission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MissionRepository extends JpaRepository<Mission, Long> {

    Page<Mission> findAllByStore_Id(Long storeId, Pageable pageable);

    @Query("SELECT m FROM Mission m WHERE m.store.region.id = :regionId")
    Page<Mission> findMissionsByRegionId(@Param("regionId") Long regionId, Pageable pageable);

    @Query("SELECT mm FROM MemberMission mm JOIN FETCH mm.mission WHERE mm.member.id = :memberId AND mm.state = :state")
    Page<MemberMission> findByMemberIdAndState(@Param("memberId") Long memberId, @Param("state") Long state, Pageable pageable);
}
