package com.example.umc10th.domain.member.controller;

import com.example.umc10th.domain.member.service.MemberMissionService;
import com.example.umc10th.domain.mission.dto.MissionResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members/{memberId}/missions")
public class MemberMissionController  implements MemberMissionControllerDocs{

    private final MemberMissionService memberMissionService;

    @GetMapping
    @Override
    public ResponseEntity<MissionResponse.MemberMissionListDTO> getMemberMissions(
            @PathVariable("memberId") Long memberId,
            @RequestParam(name = "page", defaultValue = "0") Integer page,
            @RequestParam(name = "size", defaultValue = "10") Integer size) {

        // Pageable 객체 생성
        Pageable pageable = PageRequest.of(page, size);

        // 서비스 호출을 통해 최종 조립된 dto를 바로 받음
        MissionResponse.MemberMissionListDTO response = memberMissionService.getMemberMissions(memberId, pageable);

        // 클라이언트에게 반환
        return ResponseEntity.ok(response);
    }
}
