package com.example.umc_week4.domain.mission.service;

import com.example.umc_week4.domain.mission.converter.MissionConverter;
import com.example.umc_week4.domain.mission.dto.MissionResDTO;
import com.example.umc_week4.domain.mission.entity.Mission;
import com.example.umc_week4.domain.mission.repository.MemberMissionRepository;
import com.example.umc_week4.domain.mission.repository.MissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.data.autoconfigure.web.DataWebProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MissionService {

    private final MemberMissionRepository memberMissionRepository;
    private final MissionRepository missionRepository;

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

    public Page<MissionResDTO.GetMission> getMissions(
            Long storeId,
            Integer pageSize,
            Integer pageNumber,
            String sort
    ) {
        //정렬 정보 생성
        Sort sortInfo;
        if(sort != null){
            sortInfo = Sort.by(sort);
        } else {
            sortInfo = Sort.by("id").descending();
        }

        // 페이지 정보들을 pageRequest로 만들기
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sortInfo);

        //가게 내 미션들 조회
        Page<Mission> missionList = missionRepository.findAllByStore_Id(storeId, pageRequest);

        //미션들 응답 DTO로 포장하기
        return missionList.map(MissionConverter::toGetMission);

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