package com.example.umc_week4.domain.mission.repository;

import com.example.umc_week4.domain.mission.dto.HomeMissionDTO;
import com.example.umc_week4.domain.mission.entity.Mission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.awt.print.Pageable;
import java.util.List;

public interface MissionRepository extends JpaRepository<Mission, Long> {

    @Query("SELECT new com.example.umc_week4.domain.mission.dto.HomeMissionDTO(" +
            "l.name, s.id, s.name, m.id, m.missionSpec, m.rewardPoint, m.id) " +
            "FROM Mission m " +
            "JOIN m.store s " +
            "JOIN s.location l " +
            "WHERE l.name = :locationName " +
            "AND m.deadline >= CURRENT_DATE " +
            "AND m.id < :cursor " +
            "ORDER BY m.id DESC")
    List<HomeMissionDTO> findHomeMissions(
            @Param("locationName") String locationName,
            @Param("cursor") Long cursor,
            Pageable pageable
    );

    Page<Mission> findAllByStore_Id(Long storeId, PageRequest pageRequest);
}