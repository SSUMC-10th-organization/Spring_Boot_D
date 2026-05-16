package com.example.umc10thmission4.domain.mission.service;

import com.example.umc10thmission4.domain.member.entity.mapping.MemberMission;
import com.example.umc10thmission4.domain.mission.dto.MissionReqDTO;
import com.example.umc10thmission4.domain.mission.dto.MissionResDTO;
import com.example.umc10thmission4.domain.mission.entity.Mission;
import com.example.umc10thmission4.domain.mission.repository.MissionRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class MissionService {

    private final MissionRepository missionRepository;

    // 미션 목록 조회 (컨트롤러에서 넘겨준 파라미터 규격에 맞게 대폭 수정!)
    public MissionResDTO.MissionListDTO getMissionList(Long memberId, Long state, Pageable pageable) {

        Page<MemberMission> memberMissionPage = missionRepository.findByMemberIdAndState(memberId, state, pageable);

        return null;
    }

    // 홈 화면 미션 목록 조회
    public MissionResDTO.MissionListDTO getHomeMissionList(Long regionId, int page) {
        Pageable pageable = PageRequest.of(page, 10);
        Page<Mission> missionPage = missionRepository.findMissionsByRegionId(regionId, pageable);

        // TODO: missionPage를 MissionListDTO로 변환하는 로직 필요
        return null;
    }

    @Transactional
    public void updateMissionStatus(Long missionId, MissionReqDTO.MissionStatusDTO request) {
        // 미션 상태 업데이트 로직 작성 칸
    }
}