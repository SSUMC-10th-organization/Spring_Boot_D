package com.example.umc10th.domain.member.controller;

import com.example.umc10th.domain.member.dto.HomeResponse;
import com.example.umc10th.domain.member.dto.MemberResponse;
import com.example.umc10th.domain.mission.dto.MissionResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

public interface MemberControllerDocs {

    @Operation(
            summary = "마이 화면 조회 by 매튜/진현준",
            description = "마이 화면 조회 API입니다."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "실패")
    })
    @GetMapping("/{memberId}/my-page")
    ResponseEntity<MemberResponse.MyPageDTO> getMyPage(
            @PathVariable("memberId") Long memberId);
}
