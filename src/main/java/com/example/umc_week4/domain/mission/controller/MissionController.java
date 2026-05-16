package com.example.umc_week4.domain.mission.controller;

import com.example.umc_week4.domain.mission.dto.MissionReqDTO;
import com.example.umc_week4.domain.mission.dto.MissionResDTO;
import com.example.umc_week4.domain.mission.exception.code.MissionSuccessCode;
import com.example.umc_week4.domain.mission.service.MissionService;
import com.example.umc_week4.global.apiPayload.ApiResponse;
import com.example.umc_week4.global.apiPayload.code.BaseSuccessCode;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        BaseSuccessCode code = MissionSuccessCode.CREATED;
        return ApiResponse.onSuccess(code, missionService.createMission(storeId, dto));
    }

    // 가게 내 미션들 조회
    @GetMapping("/v1/stores/{storeId}/missions")
    public ApiResponse<MissionResDTO.Pagination<MissionResDTO.GetMission>> getMissions(
            @PathVariable Long storeId,
            @RequestParam Integer pageSize,
            @RequestParam String cursor,
            @RequestParam String query
    ){
        BaseSuccessCode code = MissionSuccessCode.OK;
        return ApiResponse.onSuccess(code, missionService.getMissions(storeId, pageSize, cursor, query));
    }

    // 가게 내 미션들 조회 - 오프셋 기반
    @GetMapping("/v1/stores/{storeId}/missions/offset")
    public ApiResponse<MissionResDTO.OffsetPagination<MissionResDTO.GetMission>> getMissionsOffset(
            @PathVariable Long storeId,
            @RequestParam Integer pageSize,
            @RequestParam Integer pageNumber,
            @RequestParam(required = false) String sort
    ){
        BaseSuccessCode code = MissionSuccessCode.OK;
        return ApiResponse.onSuccess(code, missionService.getMissionsOffset(storeId, pageSize, pageNumber, sort));
    }

    // 가게 내 미션들 조회 - 페이지네이션 없음
    @GetMapping("/v1/stores/{storeId}/missions/all")
    public ApiResponse<List<MissionResDTO.GetMission>> getAllMissions(
            @PathVariable Long storeId
    ){
        BaseSuccessCode code = MissionSuccessCode.OK;
        return ApiResponse.onSuccess(code, missionService.getMissions(storeId));
    }

    // 내가 진행중인 미션 조회하기
    @GetMapping("/v1/users/me/missions")
    public ApiResponse<MissionResDTO.OffsetPagination<MissionResDTO.MyMission>> getMyMissions(
            @RequestBody @Valid MissionReqDTO.GetMyMissions dto
    ) {
        BaseSuccessCode code = MissionSuccessCode.GET_MISSION_LIST_SUCCESS;
        return ApiResponse.onSuccess(code, missionService.getMyMissions(dto));
    }


    // Swagger에서 Request Body로 조회 테스트하기 좋은 POST 별칭
    @PostMapping("/v1/users/me/missions/search")
    public ApiResponse<MissionResDTO.OffsetPagination<MissionResDTO.MyMission>> searchMyMissions(
            @RequestBody @Valid MissionReqDTO.GetMyMissions dto
    ) {
        return getMyMissions(dto);
    }

    @PatchMapping("/v1/missions/{missionId}")
    public ApiResponse<MissionResDTO.UpdateMissionStatusResult> updateMissionStatus(
            @PathVariable Long missionId,
            @RequestBody @Valid MissionReqDTO.UpdateStatus request
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
