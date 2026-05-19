package com.example.umc10th.domain.member.controller;

import com.example.umc10th.domain.member.converter.MemberConverter;
import com.example.umc10th.domain.member.dto.MemberRequest;
import com.example.umc10th.domain.member.dto.MemberResponse;
import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.exception.code.MemberSuccessCode;
import com.example.umc10th.domain.member.service.MemberService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.GeneralSuccessCode;
import jakarta.validation.Valid;
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
    public ApiResponse<MemberResponse.MyPageDTO> getMyPage(
            @PathVariable("memberId") Long memberId) {

        // Service 호출
        MemberResponse.MyPageDTO response = memberService.getMyPage(memberId);

        // 결과 반환
        return ApiResponse.onSuccess(GeneralSuccessCode.OK,response);
    }

    @PostMapping("/auth/signup")
    public ApiResponse<MemberResponse.JoinResultDTO> join (
            @RequestBody @Valid MemberRequest.JoinDTO request
    ) {
        Member member = memberService.join(request);
        return ApiResponse.onSuccess(MemberSuccessCode.OK,MemberConverter.toJoinResultDTO(member));
    }

}