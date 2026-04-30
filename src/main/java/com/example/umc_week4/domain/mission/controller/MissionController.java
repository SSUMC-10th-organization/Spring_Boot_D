package com.example.umc_week4.domain.mission.controller;

import com.example.umc_week4.domain.mission.dto.MissionReqDTO;
import com.example.umc_week4.domain.mission.dto.MissionResDTO;
import com.example.umc_week4.domain.mission.exception.code.MissionSuccessCode;
import com.example.umc_week4.global.apiPayload.ApiResponse;
import com.example.umc_week4.global.apiPayload.code.BaseSuccessCode;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class MissionController {

    // 내 미션 목록 조회
    @GetMapping("/users/me/missions")
    public ApiResponse<MissionResDTO.MissionList> getMyMissions(
            @RequestHeader("Authorization") String authorization,
            @RequestParam(defaultValue = "ongoing") String status,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size
    ) {
        BaseSuccessCode code = MissionSuccessCode.GET_MISSION_LIST_SUCCESS;

        MissionResDTO.MissionList result = new MissionResDTO.MissionList(
                status,
                page,
                size,
                false,
                List.of(
                        new MissionResDTO.MissionInfo(
                                1L,
                                10L,
                                "대야미 식당",
                                "점심 방문 후 리뷰 남기기",
                                500,
                                status
                        )
                )
        );

        return ApiResponse.onSuccess(code, result);
    }

    // 미션 상태 변경
    @PatchMapping("/missions/{missionId}")
    public ApiResponse<MissionResDTO.UpdateMissionStatusResult> updateMissionStatus(
            @RequestHeader("Authorization") String authorization,
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
}