package com.example.umc_week4.domain.mission.service;

import com.example.umc_week4.domain.mission.dto.MissionResDTO;
import com.example.umc_week4.domain.mission.repository.MemberMissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MissionService {

    private final MemberMissionRepository memberMissionRepository;

    public MissionResDTO.MissionList getMyMissions(
            Long memberId,
            String status,
            Long cursor,
            Integer size
    ) {
        String missionStatus = toMissionStatus(status);

        Long searchCursor = getSearchCursor(cursor);

        Integer searchSize = size + 1;

        List<Object[]> rows = memberMissionRepository.findMyMissions(
                memberId,
                missionStatus,
                searchCursor,
                searchSize
        );

        Boolean hasNext = rows.size() > size;
        List<MissionResDTO.MissionInfo> missions = new ArrayList<>();

        int resultSize = Math.min(rows.size(), size);
        Long nextCursor = null;

        for (int i = 0; i < resultSize; i++) {
            Object[] row = rows.get(i);

            // MemberMissionRepository.findMyMissions()의 SELECT 순서
            // 0: mission_id
            // 1: reward_point
            // 2: status
            // 3: cursor_value
            nextCursor = toLong(row[3]);

            missions.add(new MissionResDTO.MissionInfo(
                    toLong(row[0]),
                    toInteger(row[1]),
                    toStringValue(row[2]),
                    toLong(row[3])
            ));
        }

        return new MissionResDTO.MissionList(
                missionStatus,
                nextCursor,
                size,
                hasNext,
                missions
        );
    }

    private String toMissionStatus(String status) {
        if (status == null) {
            return "CHALLENGING";
        }

        if (status.equalsIgnoreCase("complete") || status.equalsIgnoreCase("completed")) {
            return "COMPLETE";
        }

        if (status.equalsIgnoreCase("challenging") || status.equalsIgnoreCase("ongoing")) {
            return "CHALLENGING";
        }


        return "CHALLENGING";
    }

    private Long getSearchCursor(Long cursor) {
        if (cursor == null || cursor == 0) {
            return Long.MAX_VALUE;
        }
        return cursor;
    }

    private Long toLong(Object value) {
        if (value == null) {
            return null;
        }
        return ((Number) value).longValue();
    }

    private Integer toInteger(Object value) {
        if (value == null) {
            return null;
        }
        return ((Number) value).intValue();
    }

    private String toStringValue(Object value) {
        if (value == null) {
            return null;
        }
        return value.toString();
    }
}