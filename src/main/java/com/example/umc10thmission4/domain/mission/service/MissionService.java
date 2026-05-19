package com.example.umc10thmission4.domain.mission.service;

import com.example.umc10thmission4.domain.member.entity.mapping.MemberMission;
import com.example.umc10thmission4.domain.member.repository.MemberMissionRepository;
import com.example.umc10thmission4.domain.mission.converter.MissionConverter;
import com.example.umc10thmission4.domain.mission.dto.MissionReqDTO;
import com.example.umc10thmission4.domain.mission.dto.MissionResDTO;
import com.example.umc10thmission4.domain.mission.entity.Mission;
import com.example.umc10thmission4.domain.mission.enums.MissionStatus;
import com.example.umc10thmission4.domain.mission.repository.MissionRepository;
import com.example.umc10thmission4.domain.store.entity.Store;
import com.example.umc10thmission4.domain.store.exception.StoreException;
import com.example.umc10thmission4.domain.store.exception.code.StoreErrorCode;
import com.example.umc10thmission4.domain.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MissionService {

    private final StoreRepository storeRepository;
    private final MissionRepository missionRepository;
    private final MemberMissionRepository memberMissionRepository;

    // 1. 가게 미션 생성
    @Transactional
    public void createMission(Long storeId, MissionReqDTO.CreateMission dto) {
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new StoreException(StoreErrorCode.NOT_FOUND));
        Mission mission = MissionConverter.toMission(store, dto);
        missionRepository.save(mission);
    }

    // 2. 가게 내 미션들 조회
    public MissionResDTO.Pagination<MissionResDTO.GetMission> getMissions(
            Long storeId,
            Integer pageSize,
            Integer pageNumber,
            String sort
    ) {
        Sort sortInfo = (sort != null) ? Sort.by(sort) : Sort.by("id").descending();
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sortInfo);
        Page<Mission> missionList = missionRepository.findAllByStore_Id(storeId, pageRequest);
        return MissionConverter.toPagination(
                missionList.map(MissionConverter::toGetMission).toList(),
                missionList.getNumber(),
                missionList.getSize()
        );
    }

    // 3. 홈 화면 미션 목록 조회
    public MissionResDTO.MissionListDTO getHomeMissionList(Long regionId, int page) {
        Pageable pageable = PageRequest.of(page, 10);
        Page<Mission> missionPage = missionRepository.findMissionsByRegionId(regionId, pageable);
        return null;
    }

    // 4. 미션 상태 수정
    @Transactional
    public void updateMissionStatus(Long missionId, MissionReqDTO.MissionStatusDTO request) {
        // 미션 상태 업데이트 로직 작성 칸
    }

    // 5. 내가 진행중인 미션 조회 (오프셋 페이지네이션)
    public MissionResDTO.Pagination<MissionResDTO.OngoingMission> getOngoingMissions(
            MissionReqDTO.GetOngoingMissionsDTO dto
    ) {
        PageRequest pageRequest = PageRequest.of(
                dto.pageNumber(),
                dto.pageSize(),
                Sort.by("id").descending()
        );
        Page<MemberMission> memberMissionPage = memberMissionRepository
                .findByMember_IdAndStatus(dto.memberId(), MissionStatus.IN_PROGRESS, pageRequest);
        return MissionConverter.toPagination(
                memberMissionPage.map(MissionConverter::toOngoingMission).toList(),
                memberMissionPage.getNumber(),
                memberMissionPage.getSize()
        );
    }
}