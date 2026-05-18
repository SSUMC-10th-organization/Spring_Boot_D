package com.example.umc_week4.domain.member.service;

import com.example.umc_week4.domain.member.converter.MemberConverter;
import com.example.umc_week4.domain.member.dto.MemberResDTO;
import com.example.umc_week4.domain.member.entity.Member;
import com.example.umc_week4.domain.member.exception.code.MemberErrorCode;
import com.example.umc_week4.domain.member.repository.MemberRepository;
import com.example.umc_week4.domain.mission.dto.HomeMissionDTO;
import com.example.umc_week4.domain.mission.entity.mapping.MemberMission;
import com.example.umc_week4.domain.mission.repository.MemberMissionRepository;
import com.example.umc_week4.domain.mission.repository.MissionRepository;
import com.example.umc_week4.global.apiPayload.exception.ProjectException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final MissionRepository missionRepository;
    private final MemberMissionRepository memberMissionRepository;

    @Transactional(readOnly = true)
    public MemberResDTO.GetInfo getMyPage(String name) {
        Member member = memberRepository.findByNameAndDeletedAtIsNull(name)
                .orElseThrow(() -> new ProjectException(MemberErrorCode.MEMBER_NOT_FOUND));

        return MemberConverter.toGetInfo(member);
    }

    @Transactional(readOnly = true)
    public MemberResDTO.HomeInfo getHome(
            Long memberId,
            String locationName,
            Long cursor,
            Integer size
    ) {
        Long searchCursor = getSearchCursor(cursor);

        Integer searchSize = size + 1;

        List<HomeMissionDTO> rows = missionRepository.findHomeMissions(
                locationName,
                searchCursor,
                PageRequest.of(0, searchSize)
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
            HomeMissionDTO row = rows.get(i);

            nextCursor = row.getCursorValue();

            missions.add(new MemberResDTO.HomeMission(
                    row.getMissionId(),
                    row.getStoreId(),
                    row.getStoreName(),
                    row.getMissionCondition(),
                    row.getMissionReward(),
                    row.getCursorValue()
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
}
