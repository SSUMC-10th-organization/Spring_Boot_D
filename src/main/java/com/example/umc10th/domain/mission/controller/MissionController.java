package com.example.umc10th.domain.mission.controller;

import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.GeneralSuccessCode;
import org.springframework.web.bind.annotation.*;

@RestController
public class MissionController {

    @GetMapping("/missions")
    public ApiResponse<String> getMissions(
            @RequestParam(name = "is_completed") Boolean isCompleted
    ) {
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, "성공");
    }

    @PatchMapping("/missions/{missionId}/done")
    public ApiResponse<String> completeMission() {
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, "성공");
    }
}
