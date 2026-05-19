package com.example.umc10jinho.domain.member.controller;

import com.example.umc10jinho.domain.member.converter.MemberConverter;
import com.example.umc10jinho.domain.member.dto.MemberReqDTO;
import com.example.umc10jinho.domain.member.dto.MemberResDTO;
import com.example.umc10jinho.domain.member.entity.Member;
import com.example.umc10jinho.domain.member.exception.code.MemberSuccessCode;
import com.example.umc10jinho.domain.member.service.MemberService;
import com.example.umc10jinho.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
@Tag(name = "멤버 API")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/signup")
    @Operation(summary = "회원가입", description = "이메일과 비밀번호로 회원가입합니다. 비밀번호는 BCrypt로 암호화됩니다.")
    public ApiResponse<MemberResDTO.SignUpResponse> signUp(
            @Valid @RequestBody MemberReqDTO.SignUpRequest request
    ) {
        Member member = memberService.signUp(request);
        return ApiResponse.onSuccess(MemberSuccessCode.SIGN_UP, MemberConverter.toSignUpResponse(member));
    }

    @GetMapping("/me")
    @Operation(summary = "마이 페이지 조회", description = "내 정보(이름, 성별, 이메일, 주소, 포인트)를 조회합니다.")
    public ApiResponse<MemberResDTO.MyInfoResponse> getMyInfo(
            @RequestParam Long memberId
    ) {
        Member member = memberService.getMyInfo(memberId);
        return ApiResponse.onSuccess(MemberSuccessCode.GET_MY_INFO, MemberConverter.toMyInfoResponse(member));
    }
}
