package com.example.umc_week4.domain.mission.controller;

import com.example.umc_week4.domain.mission.dto.MissionReqDTO;
import com.example.umc_week4.domain.mission.dto.MissionResDTO;
import com.example.umc_week4.domain.mission.exception.code.MissionSuccessCode;
import com.example.umc_week4.domain.mission.service.MissionService;
import com.example.umc_week4.global.apiPayload.ApiResponse;
import com.example.umc_week4.global.apiPayload.code.BaseSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class MissionController {

    private final MissionService missionService;

    @GetMapping("/users/me/missions")
    public ApiResponse<MissionResDTO.MissionList> getMyMissions(
            @RequestParam(defaultValue = "1") Long memberId,
            @RequestParam(defaultValue = "CHALLENGING") String status,
            @RequestParam(defaultValue = "0") Long cursor,
            @RequestParam(defaultValue = "15") Integer size
    ) {
        BaseSuccessCode code = MissionSuccessCode.GET_MISSION_LIST_SUCCESS;
        MissionResDTO.MissionList result = missionService.getMyMissions(
                memberId,
                status,
                cursor,
                size
        );

        return ApiResponse.onSuccess(code, result);
    }

    @PatchMapping("/missions/{missionId}")
    public ApiResponse<MissionResDTO.UpdateMissionStatusResult> updateMissionStatus(
            @PathVariable Long missionId,
            @RequestBody MissionReqDTO.UpdateStatus request
    ) {
        BaseSuccessCode code = MissionSuccessCode.UPDATE_MISSION_STATUS_SUCCESS;

        MissionResDTO.UpdateMissionStatusResult result = new MissionResDTO.UpdateMissionStatusResult(
                missionId,
                request.storeId(),
                request.storeName(),
                request.status()
        );

        return ApiResponse.onSuccess(code, result);
    }

    @GetMapping("v1/stores/{storeId}/missions")
    public ApiResponse<Page<MissionResDTO.Getmission>> getMissions(
            @PathVariable Long storeId,
            @RequestParam Integer pageSize,
            @RequestParam Integer pageNumber,
            @RequestParam(required = false) String sort
    ) {
        BaseSuccessCode code = MissionSuccessCode.OK;
        return ApiResponse.onSuccess(code, missionService.getMission(storeId, pageSize, pageNumber, sort));
    }
}