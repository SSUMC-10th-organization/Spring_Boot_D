package com.example.umc10jinho.domain.mission.converter;

import com.example.umc10jinho.domain.member.entity.mapping.MemberMission;
import com.example.umc10jinho.domain.mission.dto.MissionResDTO;
import com.example.umc10jinho.domain.mission.entity.Mission;
import org.springframework.data.domain.Page;

import java.util.List;

public class MissionConverter {

    public static MissionResDTO.MyMissionPreview toMyMissionPreview(MemberMission mm) {
        return new MissionResDTO.MyMissionPreview(
                mm.getId(),
                mm.getMission().getId(),
                mm.getMission().getMissionSpec(),
                mm.getMission().getPoint(),
                mm.getMission().getDeadline(),
                mm.getMission().getMarket().getName(),
                mm.getStatus()
        );
    }

    public static MissionResDTO.MyMissionListResponse toMyMissionListResponse(Page<MemberMission> page) {
        List<MissionResDTO.MyMissionPreview> previews = page.getContent().stream()
                .map(mm -> new MissionResDTO.MyMissionPreview(
                        mm.getId(),
                        mm.getMission().getId(),
                        mm.getMission().getMissionSpec(),
                        mm.getMission().getPoint(),
                        mm.getMission().getDeadline(),
                        mm.getMission().getMarket().getName(),
                        mm.getStatus()
                ))
                .toList();

        return new MissionResDTO.MyMissionListResponse(
                previews,
                page.getNumber(),
                page.getTotalPages(),
                page.getTotalElements(),
                page.hasNext()
        );
    }

    public static MissionResDTO.HomeMissionListResponse toHomeMissionListResponse(Page<Mission> page) {
        List<MissionResDTO.HomeMissionPreview> previews = page.getContent().stream()
                .map(m -> new MissionResDTO.HomeMissionPreview(
                        m.getId(),
                        m.getMissionSpec(),
                        m.getPoint(),
                        m.getDeadline(),
                        m.getMarket().getName(),
                        m.getMarket().getMarketCategory() != null
                                ? m.getMarket().getMarketCategory().getName()
                                : null
                ))
                .toList();

        return new MissionResDTO.HomeMissionListResponse(
                previews,
                page.getNumber(),
                page.getTotalPages(),
                page.getTotalElements(),
                page.hasNext()
        );
    }
}
