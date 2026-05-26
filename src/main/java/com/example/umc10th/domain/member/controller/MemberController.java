package com.example.umc10th.domain.member.controller;

import com.example.umc10th.domain.member.converter.MemberConverter;
import com.example.umc10th.domain.member.dto.MemberRequest;
import com.example.umc10th.domain.member.dto.MemberResponse;
import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.exception.code.MemberSuccessCode;
import com.example.umc10th.domain.member.service.MemberService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.GeneralSuccessCode;
import com.example.umc10th.global.security.entity.AuthMember;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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

    @GetMapping("/mypage")
    public ApiResponse<MemberResponse.MyPageDTO> getMyPage(
            @AuthenticationPrincipal AuthMember authMember
    ) {
        // @AuthenticationPrincipal을 통해 현재 로그인한 사용자의 이메일을 꺼냅니다.
        MemberResponse.MyPageDTO result = memberService.getMyPage(authMember.getUsername());
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, result);
    }

}