package com.example.umc10jinho.domain.member.controller;

import com.example.umc10jinho.domain.member.dto.MemberResDTO;
import com.example.umc10jinho.domain.member.exception.code.MemberSuccessCode;
import com.example.umc10jinho.global.apiPayload.ApiResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/members")
public class MemberController {

    @GetMapping("/me")
    public ApiResponse<MemberResDTO.MyInfoResponse> getMyInfo() {
        MemberResDTO.MyInfoResponse response =
                new MemberResDTO.MyInfoResponse(
                        1L,
                        "김진호",
                        "MALE",
                        "jinho040905@gmail.com",
                        "서울특별시 동작구",
                        0
                );

        return ApiResponse.onSuccess(MemberSuccessCode.GET_MY_INFO, response);
    }
}