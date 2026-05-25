package com.example.umc10jinho.domain.market.controller;

import com.example.umc10jinho.domain.mission.converter.MissionConverter;
import com.example.umc10jinho.domain.mission.dto.MissionResDTO;
import com.example.umc10jinho.domain.mission.entity.Mission;
import com.example.umc10jinho.domain.mission.exception.code.MissionSuccessCode;
import com.example.umc10jinho.domain.mission.service.MissionService;
import com.example.umc10jinho.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Tag(name = "홈 화면 API")
public class MarketController {

    private final MissionService missionService;

    @GetMapping("/home")
    @Operation(summary = "홈 화면 미션 목록 조회", description = "선택된 지역에서 도전 가능한 미션 목록을 페이징으로 조회합니다.")
    public ApiResponse<MissionResDTO.HomeMissionListResponse> getHome(
            @RequestParam Long regionId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Page<Mission> missions = missionService.getMissionsByRegion(regionId, page, size);
        return ApiResponse.onSuccess(
                MissionSuccessCode.GET_HOME_MISSIONS,
                MissionConverter.toHomeMissionListResponse(missions)
        );
    }
}
