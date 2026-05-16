package com.example.umc10th.domain.member.service;

import com.example.umc10th.domain.member.converter.MemberMissionConverter;
import com.example.umc10th.domain.member.entity.mapping.MemberMission;
import com.example.umc10th.domain.member.exception.code.MemberErrorCode;
import com.example.umc10th.domain.member.repository.MemberMissionRepository;
import com.example.umc10th.domain.member.repository.MemberRepository;
import com.example.umc10th.domain.mission.dto.MissionResponse;
import com.example.umc10th.global.apiPayload.exception.GeneralException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberMissionService {

    private final MemberMissionRepository memberMissionRepository;
    private final MemberRepository memberRepository;

    public MissionResponse.MemberMissionListDTO getMemberMissions(Long memberId, Pageable pageable) {

        // 1. 회원 존재 여부 검증
        if (!memberRepository.existsById(memberId)) {
            throw new IllegalArgumentException("존재하지 않는 회원입니다.");
        }

        // 2. 미션 목록 페이징 조회
        Page<MemberMission> memberMissionPage = memberMissionRepository.findAllByMemberId(memberId, pageable);

        // 3. 컨버터를 사용하여 Entity -> DTO 매핑 및 최종 응답 객체 반환
        return MemberMissionConverter.toMemberMissionListDTO(memberMissionPage);
    }

    @Transactional(readOnly = true)
    public MissionResponse.MemberMissionListDTO getOngoingMissions(Long memberId, Pageable pageable) {
        // 1. 회원 존재 여부 검증
        if (!memberRepository.existsById(memberId)) {
            throw new GeneralException(MemberErrorCode.NOT_FOUND);
        }

        // 2. 기존 메서드 활용 (isCompleted = false)
        Page<MemberMission> ongoingMissions =
                memberMissionRepository.findAllByMemberIdAndIsCompleted(memberId, false, pageable);

        // 3. 변환 후 반환
        return MemberMissionConverter.toMemberMissionListDTO(ongoingMissions);
    }


}