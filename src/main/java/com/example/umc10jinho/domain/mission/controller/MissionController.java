package com.example.umc10jinho.domain.mission.controller;

import com.example.umc10jinho.domain.member.entity.mapping.MemberMission;
import com.example.umc10jinho.domain.mission.converter.MissionConverter;
import com.example.umc10jinho.domain.mission.dto.MissionReqDTO;
import com.example.umc10jinho.domain.mission.dto.MissionResDTO;
import com.example.umc10jinho.domain.mission.enums.MissionStatus;
import com.example.umc10jinho.domain.mission.exception.code.MissionSuccessCode;
import com.example.umc10jinho.domain.mission.service.MissionService;
import com.example.umc10jinho.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/missions")
@RequiredArgsConstructor
@Tag(name = "미션 API")
public class MissionController {

    private final MissionService missionService;

    @GetMapping("/my")
    @Operation(summary = "내 미션 목록 조회", description = "진행중(CHALLENGING) 또는 완료(COMPLETE) 미션 목록을 페이징으로 조회합니다.")
    public ApiResponse<MissionResDTO.MyMissionListResponse> getMyMissions(
            @RequestParam Long memberId,
            @RequestParam(defaultValue = "CHALLENGING") MissionStatus status,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Page<MemberMission> myMissions = missionService.getMyMissions(memberId, status, page, size);
        return ApiResponse.onSuccess(
                MissionSuccessCode.GET_MY_MISSIONS,
                MissionConverter.toMyMissionListResponse(myMissions)
        );
    }

    @PostMapping("/challenge")
    @Operation(summary = "미션 도전하기", description = "특정 미션에 도전을 시작합니다.")
    public ApiResponse<MissionResDTO.MyMissionPreview> challengeMission(
            @RequestParam Long memberId,
            @RequestBody MissionReqDTO.ChallengeMissionRequest request
    ) {
        MemberMission memberMission = missionService.challengeMission(memberId, request.missionId());
        return ApiResponse.onSuccess(
                MissionSuccessCode.CHALLENGE_MISSION,
                MissionConverter.toMyMissionPreview(memberMission)
        );
    }
}
