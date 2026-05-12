package com.example.umc10th.domain.mission.controller;

import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.enums.MissionStatus;
import com.example.umc10th.domain.mission.service.MissionService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members/{memberId}/missions")
public class MissionController {

    private final MissionService missionService;

    @GetMapping
    public ApiResponse<MissionResDTO.MissionPreviewListResponse> getMyMissions(
            @PathVariable Long memberId,
            @RequestParam MissionStatus status,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size
    ) {
        return ApiResponse.onSuccess(missionService.getMyMissions(memberId, status, page, size));
    }
}
