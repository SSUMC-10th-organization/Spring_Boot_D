package com.example.umc10th.domain.mission.service;

import com.example.umc10th.domain.mission.converter.MissionConverter;
import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.entity.mapping.MemberMission;
import com.example.umc10th.domain.mission.enums.MissionStatus;
import com.example.umc10th.domain.mission.repository.MemberMissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MissionService {

    private final MemberMissionRepository memberMissionRepository;

    public MissionResDTO.MissionPreviewListResponse getMyMissions(
            Long memberId,
            MissionStatus status,
            Integer page,
            Integer size
    ) {
        Page<MemberMission> missionPage = memberMissionRepository.findMyMissions(
                memberId,
                status,
                PageRequest.of(page, size)
        );

        return MissionConverter.toMissionPreviewListResponse(missionPage);
    }
}