package com.example.umc10jinho.domain.market.controller;

import com.example.umc10jinho.domain.market.dto.MarketResDTO;
import com.example.umc10jinho.domain.market.exception.code.MarketSuccessCode;
import com.example.umc10jinho.global.apiPayload.ApiResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MarketController {

    @GetMapping("/home")
    public ApiResponse<MarketResDTO.HomeResponse> getHome() {
        MarketResDTO.HomeResponse response =
                new MarketResDTO.HomeResponse(
                        List.of(
                                new MarketResDTO.MarketPreview(
                                        1L,
                                        "맛있는 식당",
                                        "서울특별시 동작구",
                                        "한식",
                                        "https://example.com/market.jpg"
                                )
                        )
                );

        return ApiResponse.onSuccess(MarketSuccessCode.GET_HOME, response);
    }
}