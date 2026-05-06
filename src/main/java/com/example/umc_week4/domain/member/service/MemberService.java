package com.example.umc_week4.domain.member.service;

import com.example.umc_week4.domain.member.converter.MemberConverter;
import com.example.umc_week4.domain.member.dto.MemberResDTO;
import com.example.umc_week4.domain.member.entity.Member;
import com.example.umc_week4.domain.member.repository.MemberRepository;
import com.example.umc_week4.domain.mission.entity.mapping.MemberMission;
import com.example.umc_week4.domain.mission.repository.MemberMissionRepository;
import com.example.umc_week4.domain.mission.repository.MissionRepository;
import com.example.umc_week4.global.apiPayload.code.GeneralErrorCode;
import com.example.umc_week4.global.apiPayload.exception.ProjectException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final MissionRepository missionRepository;
    private final MemberMissionRepository memberMissionRepository;

    public MemberResDTO.GetInfo getMyPage(String name) {
        Member member = memberRepository.findByNameAndDeletedAtIsNull(name)
                .orElseThrow(() -> new ProjectException(GeneralErrorCode.NOT_FOUND));

        return MemberConverter.toGetInfo(member);
    }

    public MemberResDTO.HomeInfo getHome(
            Long memberId,
            String locationName,
            Long cursor,
            Integer size
    ) {
        Long searchCursor = getSearchCursor(cursor);

        Integer searchSize = size + 1;

        List<Object[]> rows = missionRepository.findHomeMissions(
                locationName,
                searchCursor,
                searchSize
        );

        Boolean hasNext = rows.size() > size;
        List<MemberResDTO.HomeMission> missions = new ArrayList<>();

        Long totalCompletedCount = memberMissionRepository.countByMember_IdAndStatus(
                memberId,
                MemberMission.Status.COMPLETE
        );

        int resultSize = Math.min(rows.size(), size);
        Long nextCursor = null;

        for (int i = 0; i < resultSize; i++) {
            Object[] row = rows.get(i);

            // MissionRepository.findHomeMissions()의 SELECT 순서
            // 0: location
            // 1: store_id
            // 2: store_name
            // 3: mission_id
            // 4: mission_condition
            // 5: mission_reward
            // 6: cursor_value
            nextCursor = toLong(row[6]);

            missions.add(new MemberResDTO.HomeMission(
                    toLong(row[3]),
                    toLong(row[1]),
                    toStringValue(row[2]),
                    toStringValue(row[4]),
                    toInteger(row[5]),
                    toLong(row[6])
            ));
        }

        return new MemberResDTO.HomeInfo(
                locationName,
                totalCompletedCount,
                nextCursor,
                hasNext,
                missions
        );
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