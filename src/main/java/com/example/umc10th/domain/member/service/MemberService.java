package com.example.umc10th.domain.member.service;

import com.example.umc10th.domain.member.converter.MemberConverter;
import com.example.umc10th.domain.member.dto.MemberResDTO;
import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.repository.MemberRepository;
import com.example.umc10th.domain.mission.converter.MissionConverter;
import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.enums.MissionStatus;
import com.example.umc10th.domain.mission.repository.MemberMissionRepository;
import com.example.umc10th.domain.mission.repository.MissionRepository;
import com.example.umc10th.domain.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;
    private final ReviewRepository reviewRepository;
    private final MemberMissionRepository memberMissionRepository;
    private final MissionRepository missionRepository;

    public MemberResDTO.MyPageResponse getMyPage(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));

        Long reviewCount = reviewRepository.countByMemberIdAndDeletedAtIsNull(memberId);

        return MemberConverter.toMyPageResponse(member, reviewCount);
    }

    public MemberResDTO.HomeResponse getHome(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));

        Long totalMissionCount = memberMissionRepository.countByMemberId(memberId);
        Long completedMissionCount = memberMissionRepository.countByMemberIdAndStatus(
                memberId,
                MissionStatus.COMPLETED
        );

        List<MissionResDTO.HomeMissionDTO> missionList = missionRepository.findHomeMissions(
                        memberId,
                        PageRequest.of(0, 2)
                )
                .getContent()
                .stream()
                .map(MissionConverter::toHomeMissionDTO)
                .toList();

        return MemberConverter.toHomeResponse(
                member,
                totalMissionCount,
                completedMissionCount,
                missionList
        );
    }
}