package com.example.umc10jinho.domain.mission.service;

import com.example.umc10jinho.domain.member.entity.Member;
import com.example.umc10jinho.domain.member.entity.mapping.MemberMission;
import com.example.umc10jinho.domain.member.repository.MemberMissionRepository;
import com.example.umc10jinho.domain.member.repository.MemberRepository;
import com.example.umc10jinho.domain.mission.entity.Mission;
import com.example.umc10jinho.domain.mission.enums.MissionStatus;
import com.example.umc10jinho.domain.mission.exception.MissionException;
import com.example.umc10jinho.domain.mission.exception.code.MissionErrorCode;
import com.example.umc10jinho.domain.mission.repository.MissionRepository;
import com.example.umc10jinho.global.apiPayload.code.GeneralErrorCode;
import com.example.umc10jinho.global.apiPayload.exception.ProjectException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MissionService {

    private final MemberMissionRepository memberMissionRepository;
    private final MissionRepository missionRepository;
    private final MemberRepository memberRepository;

    public Page<MemberMission> getMyMissions(Long memberId, MissionStatus status, int page, int size) {
        memberRepository.findById(memberId)
                .orElseThrow(() -> new ProjectException(GeneralErrorCode.NOT_FOUND));
        Pageable pageable = PageRequest.of(page, size);
        return memberMissionRepository.findByMemberIdAndStatus(memberId, status, pageable);
    }

    public Page<Mission> getMissionsByRegion(Long regionId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return missionRepository.findMissionsByRegionId(regionId, pageable);
    }

    @Transactional
    public MemberMission challengeMission(Long memberId, Long missionId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new ProjectException(GeneralErrorCode.NOT_FOUND));
        Mission mission = missionRepository.findById(missionId)
                .orElseThrow(() -> new MissionException(MissionErrorCode.MISSION_NOT_FOUND));

        boolean alreadyChallenging = memberMissionRepository
                .findByMemberIdAndStatus(memberId, MissionStatus.CHALLENGING, PageRequest.of(0, Integer.MAX_VALUE))
                .getContent().stream()
                .anyMatch(mm -> mm.getMission().getId().equals(missionId));

        if (alreadyChallenging) {
            throw new MissionException(MissionErrorCode.ALREADY_CHALLENGING);
        }

        MemberMission memberMission = MemberMission.builder()
                .member(member)
                .mission(mission)
                .status(MissionStatus.CHALLENGING)
                .build();

        return memberMissionRepository.save(memberMission);
    }
}
