package com.example.umc_week4.domain.mission.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MissionReqDTO {

    // 미션 상태 변경
    public record UpdateStatus(
            String status,

            @JsonProperty("store_id")
            Long storeId,

            @JsonProperty("store_name")
            String storeName
    ) {
    }
}