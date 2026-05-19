package com.example.umc10thmission4.domain.mission.controller;

import com.example.umc10thmission4.domain.mission.dto.MissionReqDTO;
import com.example.umc10thmission4.domain.mission.dto.MissionResDTO;
import com.example.umc10thmission4.domain.mission.service.MissionService;
import com.example.umc10thmission4.global.apiPayload.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MissionController {

    private final MissionService missionService;

    // 가게 미션 생성
    @PostMapping("/v1/stores/{storeId}/missions")
    public ApiResponse<Void> createMission(
            @PathVariable Long storeId,
            @RequestBody @Valid MissionReqDTO.CreateMission dto
    ){
        missionService.createMission(storeId, dto);
        return ApiResponse.onSuccess(null);
    }

    // 가게 내 미션들 조회
    @GetMapping("/v1/stores/{storeId}/missions")
    public ApiResponse<MissionResDTO.Pagination<MissionResDTO.GetMission>> getMissions(
            @PathVariable Long storeId,
            @RequestParam Integer pageSize,
            @RequestParam Integer pageNumber,
            @RequestParam(required = false) String sort
    ){
        return ApiResponse.onSuccess(missionService.getMissions(storeId, pageSize, pageNumber, sort));
    }

    // 미션 성공 처리
    @PatchMapping("/{missionId}/status")
    public ApiResponse<String> updateMissionStatus(
            @PathVariable Long missionId,
            @RequestBody MissionReqDTO.MissionStatusDTO request
    ) {
        missionService.updateMissionStatus(missionId, request);
        return ApiResponse.onSuccess("미션 상태가 업데이트되었습니다.");
    }

    // 진행중인 미션 조회
    @PostMapping("/v1/members/missions/ongoing")
    public ApiResponse<MissionResDTO.Pagination<MissionResDTO.OngoingMission>> getOngoingMissions(
            @RequestBody @Valid MissionReqDTO.GetOngoingMissionsDTO dto
    ) {
        return ApiResponse.onSuccess(missionService.getOngoingMissions(dto));
    }
}