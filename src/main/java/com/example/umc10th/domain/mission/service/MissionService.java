package com.example.umc10th.domain.mission.service;

import com.example.umc10th.domain.mission.converter.MissionConverter;
import com.example.umc10th.domain.mission.dto.MissionReqDTO;
import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.entity.Mission;
import com.example.umc10th.domain.mission.entity.Store;
import com.example.umc10th.domain.mission.entity.mapping.MemberMission;
import com.example.umc10th.domain.mission.enums.MissionStatus;
import com.example.umc10th.domain.mission.exception.MissionException;
import com.example.umc10th.domain.mission.exception.StoreException;
import com.example.umc10th.domain.mission.exception.code.MissionErrorCode;
import com.example.umc10th.domain.mission.exception.code.StoreErrorCode;
import com.example.umc10th.domain.mission.repository.MemberMissionRepository;
import com.example.umc10th.domain.mission.repository.MissionRepository;
import com.example.umc10th.domain.mission.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MissionService {

    private final StoreRepository storeRepository;
    private final MissionRepository missionRepository;
    private final MemberMissionRepository memberMissionRepository;

    // 가게 미션 생성
    @Transactional
    public Void createMission(
            Long storeId,
            MissionReqDTO.CreateMission dto
    ) {
        // 가게 찾기
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new StoreException(StoreErrorCode.NOT_FOUND));

        // 미션 생성
        Mission mission = MissionConverter.toMission(store, dto);

        // 미션 DB 저장
        missionRepository.save(mission);
        return null;
    }

    @Transactional(readOnly = true)
    public MissionResDTO.Pagination<MissionResDTO.GetMission> getMissions(
            Long storeId,
            Integer pageSize,
            String cursor,
            String query
    ) {
        PageRequest pageRequest = PageRequest.of(0, pageSize);

        Slice<Mission> missionList;

        if (cursor == null || cursor.equals("-1")) {
            missionList = missionRepository.findMissionByStore_IdOrderByIdDesc(
                    storeId,
                    pageRequest
            );
        } else {
            String[] cursorSplit = cursor.split(":");

            if (cursorSplit.length != 2) {
                throw new MissionException(MissionErrorCode.QUERY_NOT_VALID);
            }

            String queryType = query == null ? "id" : query.toLowerCase();

            switch (queryType) {
                case "id" -> {
                    Long idCursor = Long.parseLong(cursorSplit[1]);

                    missionList = missionRepository.findMissionByStore_IdAndIdLessThanOrderByIdDesc(
                            storeId,
                            idCursor,
                            pageRequest
                    );
                }
                default -> throw new MissionException(MissionErrorCode.QUERY_NOT_VALID);
            }
        }

        String nextCursor = null;

        if (!missionList.getContent().isEmpty()) {
            List<Mission> content = missionList.getContent();
            Mission lastMission = content.get(content.size() - 1);

            nextCursor = lastMission.getId() + ":" + lastMission.getId();
        }

        return MissionConverter.toPagination(
                missionList.getContent()
                        .stream()
                        .map(MissionConverter::toGetMission)
                        .toList(),
                missionList.hasNext(),
                nextCursor,
                missionList.getSize()
        );
    }

    // 내가 진행중인 미션 조회 - 오프셋 기반 페이지네이션
    @Transactional(readOnly = true)
    public MissionResDTO.OffsetPagination<MissionResDTO.OngoingMissionDTO> getOngoingMissions(
            MissionReqDTO.OngoingMissionRequest request
    ) {
        Sort sortInfo;

        if (request.sort() != null && !request.sort().isBlank()) {
            sortInfo = Sort.by(request.sort()).descending();
        } else {
            sortInfo = Sort.by("id").descending();
        }

        PageRequest pageRequest = PageRequest.of(
                request.pageNumber(),
                request.pageSize(),
                sortInfo
        );

        Page<MemberMission> memberMissionPage = memberMissionRepository.findOngoingMissionsByMemberId(
                request.memberId(),
                MissionStatus.ONGOING,
                pageRequest
        );

        Page<MissionResDTO.OngoingMissionDTO> dtoPage = memberMissionPage.map(
                MissionConverter::toOngoingMissionDTO
        );

        return MissionConverter.toOffsetPagination(dtoPage);
    }
}
//        // 정렬 정보 생성
//        Sort sortInfo;
//        if (sort != null){
//            sortInfo = Sort.by(sort);
//        } else {
//            sortInfo = Sort.by("id").descending();
//        }
//
//        // 페이지 정보들을 PageRequest로 만들기
//        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sortInfo);
//
//        // 가게 내 미션들 조회
//        Page<Mission> missionList = missionRepository.findAllByStore_Id(storeId, pageRequest);
//
//        // 미션들 응답 DTO로 포장하기
//        return MissionConverter.toPagination(
//                missionList.map(MissionConverter::toGetMission).toList(),
//                missionList.getNumber(),
//                missionList.getSize()
//        );
//    }