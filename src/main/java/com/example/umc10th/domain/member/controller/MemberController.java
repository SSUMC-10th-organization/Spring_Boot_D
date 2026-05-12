package com.example.umc10th.domain.member.controller;

import com.example.umc10th.domain.member.dto.MemberReqDTO;
import com.example.umc10th.domain.member.dto.MemberResDTO;
import com.example.umc10th.domain.member.exception.code.MemberSuccessCode;
import com.example.umc10th.domain.member.service.MemberService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.BaseSuccesscode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class MemberController {

    @PostMapping("/me")
    public ApiResponse<MemberResDTO.MyPageResponse> getMyPage(
            @RequestBody MemberReqDTO.MyPageRequest request
    ) {
        MemberResDTO.MyPageResponse response = MemberResDTO.MyPageResponse.builder()
                .name("nickname012")
                .profileUrl("https://example.com/profile.png")
                .email("example@email.com")
                .phoneNumber(null)
                .point(2500)
                .build();

        return ApiResponse.onSuccess(MemberSuccessCode.GET_MY_PAGE_SUCCESS, response);
    }
}
