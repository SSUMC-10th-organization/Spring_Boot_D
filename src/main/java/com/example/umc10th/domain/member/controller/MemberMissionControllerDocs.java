package com.example.umc10th.domain.member.controller;

import com.example.umc10th.domain.member.dto.MemberRequest;
import com.example.umc10th.domain.mission.dto.MissionResponse;
import com.example.umc10th.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

public interface MemberMissionControllerDocs {

    @Operation(
            summary = "멤버 미션 조회 by 매튜/진현준",
            description = "멤버 미션 조회 API입니다."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "실패")
    })
    @GetMapping
    ApiResponse<MissionResponse.MemberMissionListDTO> getMemberMissions(
            @PathVariable("memberId") Long memberId,
            @RequestParam(name = "page", defaultValue = "0") Integer page,
            @RequestParam(name = "size", defaultValue = "10") Integer size);

    @Operation(
            summary = "멤버 진행중인 미션 조회 by 매튜/진현준",
            description = "멤버 진행중인 미션 조회 API입니다."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "실패")
    })
    @GetMapping("/ongoing")
    ApiResponse<MissionResponse.MemberMissionListDTO> getOngoingMissions(
            @RequestBody MemberRequest.OngoingMissionDTO request,
            @RequestParam(name = "page", defaultValue = "0") Integer page,
            @RequestParam(name = "size", defaultValue = "10") Integer size);
}
