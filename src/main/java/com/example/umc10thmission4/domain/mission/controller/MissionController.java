package com.example.umc10thmission4.domain.mission.controller;

import com.example.umc10thmission4.domain.mission.dto.MissionReqDTO;
import com.example.umc10thmission4.domain.mission.dto.MissionResDTO;
import com.example.umc10thmission4.domain.mission.service.MissionService;
import com.example.umc10thmission4.global.apiPayload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users/missions")
public class MissionController {

    private final MissionService missionService;

    // 미션 목록 조회 (Query Parameter 사용)
    @GetMapping("")
    public ApiResponse<MissionResDTO.MissionListDTO> getMissionList(
            @RequestParam(name = "memberId") Long memberId,
            @RequestParam(name = "state") Long state,
            @PageableDefault(size = 10) Pageable pageable
    ) {
        return ApiResponse.onSuccess(missionService.getMissionList(memberId, state, pageable));
    }

    // 미션 성공 처리 (Path Variable & Request Body 사용)
    @PatchMapping("/{missionId}/status")
    public ApiResponse<String> updateMissionStatus(
            @PathVariable(name = "missionId") Long missionId,
            @RequestBody MissionReqDTO.MissionStatusDTO request
    ) {
        missionService.updateMissionStatus(missionId, request);
        return ApiResponse.onSuccess("미션 상태가 업데이트되었습니다.");
    }
}