package com.example.umc_week4.domain.mission.repository;

import com.example.umc_week4.domain.mission.entity.Mission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MissionRepository extends JpaRepository<Mission, Long> {

    @Query(value = "SELECT " +
            "l.name AS location, " +
            "s.id AS store_id, " +
            "s.name AS store_name, " +
            "m.id AS mission_id, " +
            "m.mission_spec AS mission_condition, " +
            "m.reward_point AS mission_reward, " +
            "m.id AS cursor_value " +
            "FROM mission m " +
            "JOIN store s ON m.store_id = s.id " +
            "JOIN location l ON s.location_id = l.id " +
            "WHERE l.name = :locationName " +
            "AND m.deadline >= CURDATE() " +
            "AND m.id < :cursor " +
            "ORDER BY m.id DESC " +
            "LIMIT :size", nativeQuery = true)
    List<Object[]> findHomeMissions(
            @Param("locationName") String locationName,
            @Param("cursor") Long cursor,
            @Param("size") Integer size
    );
}