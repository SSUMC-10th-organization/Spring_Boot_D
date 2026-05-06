package com.example.umc10th.domain.member.controller;

import com.example.umc10th.domain.member.dto.MemberResponse;
import com.example.umc10th.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController implements MemberControllerDocs{

    private final MemberService memberService;

    @GetMapping("/{memberId}/my-page")
    @Override
    public ResponseEntity<MemberResponse.MyPageDTO> getMyPage(
            @PathVariable("memberId") Long memberId) {

        // Service 호출
        MemberResponse.MyPageDTO response = memberService.getMyPage(memberId);

        // 결과 반환
        return ResponseEntity.ok(response);
    }
}