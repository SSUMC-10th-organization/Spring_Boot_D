package com.example.umc_week4.domain.mission.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class HomeMissionDTO {
    private String location;
    private Long storeId;
    private String storeName;
    private Long missionId;
    private String missionCondition;
    private Integer missionReward;
    private Long cursorValue;
}