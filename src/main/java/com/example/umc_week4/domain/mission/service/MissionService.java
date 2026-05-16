package com.example.umc_week4.domain.mission.service;

import com.example.umc_week4.domain.mission.converter.MissionConverter;
import com.example.umc_week4.domain.mission.dto.MissionReqDTO;
import com.example.umc_week4.domain.mission.dto.MissionResDTO;
import com.example.umc_week4.domain.mission.entity.Mission;
import com.example.umc_week4.domain.mission.entity.Store;
import com.example.umc_week4.domain.mission.entity.mapping.MemberMission;
import com.example.umc_week4.domain.mission.exception.MissionException;
import com.example.umc_week4.domain.mission.exception.StoreException;
import com.example.umc_week4.domain.mission.exception.code.MissionErrorCode;
import com.example.umc_week4.domain.mission.exception.code.StoreErrorCode;
import com.example.umc_week4.domain.mission.repository.MemberMissionRepository;
import com.example.umc_week4.domain.mission.repository.MissionRepository;
import com.example.umc_week4.domain.mission.repository.StoreRepository;
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

    // 가게 내 미션들 조회
    @Transactional(readOnly = true)
    public List<MissionResDTO.GetMission> getMissions(
            Long storeId
    ) {

        // 가게 내 미션들 조회
        List<Mission> missionList = missionRepository.findAllByStore_Id(storeId);

        // 미션들 응답 DTO로 포장하기
        return missionList.stream()
                .map(MissionConverter::toGetMission)
                .toList();
    }

    // 가게 내 미션들 조회 - 오프셋 기반 페이지네이션
    @Transactional(readOnly = true)
    public MissionResDTO.OffsetPagination<MissionResDTO.GetMission> getMissionsOffset(
            Long storeId,
            Integer pageSize,
            Integer pageNumber,
            String sort
    ) {

        // 정렬 정보 생성
        Sort sortInfo;
        if (sort != null){
            sortInfo = Sort.by(sort);
        } else {
            sortInfo = Sort.by("id").descending();
        }

        // 페이지 정보들을 PageRequest로 만들기
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sortInfo);

        // 가게 내 미션들 조회
        Page<Mission> missionList = missionRepository.findAllByStore_Id(storeId, pageRequest);

        // 미션들 응답 DTO로 포장하기
        return MissionConverter.toOffsetPagination(missionList.map(MissionConverter::toGetMission));
    }

    // 가게 내 미션들 조회
    @Transactional(readOnly = true)
    public MissionResDTO.Pagination<MissionResDTO.GetMission> getMissions(
            Long storeId,
            Integer pageSize,
            String cursor,
            String query
    ) {
        // 페이지 정보들을 PageRequest로 만들기
        PageRequest pageRequest = PageRequest.of(0, pageSize);

        long idCursor;
        Slice<Mission> missionList;
        String nextCursor;

        // 커서가 있는 경우
        if (!cursor.equals("-1")){

            // 커서 분리
            String[] cursorSplit = cursor.split(":");
            try {
                switch(query.toLowerCase()){
                    case "id":

                        // 커서 타입 변환
                        Long prevCursor = Long.parseLong(cursorSplit[0]);
                        idCursor = Long.parseLong(cursorSplit[1]);

                        // 가게 내 미션들 조회 & where절에 커서값 기입
                        missionList = missionRepository.findMissionsByStore_IdAndIdLessThanOrderByIdDesc(
                                storeId,
                                idCursor,
                                pageRequest
                        );
                        break;
                    default:
                        throw new MissionException(MissionErrorCode.QUERY_NOT_VALID);
                }
            } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
                throw new MissionException(MissionErrorCode.CURSOR_NOT_VALID);
            }
        } else {
            // 커서 없이 조회
            missionList = missionRepository.findMissionsByStore_IdOrderByIdDesc(storeId, pageRequest);
        }

        // 다음 커서 계산
        nextCursor = missionList.hasContent()
                ? missionList.getContent().get(missionList.getContent().size() - 1).getId() + ":" + missionList.getContent().get(missionList.getContent().size() - 1).getId()
                : null;

        // 미션들 응답 DTO로 포장하기
        return MissionConverter.toPagination(
                missionList.map(MissionConverter::toGetMission).toList(),
                missionList.hasNext(),
                nextCursor,
                missionList.getSize()
        );
    }

    // 내가 진행중인 미션 조회하기 - 오프셋 기반 페이지네이션
    @Transactional(readOnly = true)
    public MissionResDTO.OffsetPagination<MissionResDTO.MyMission> getMyMissions(
            MissionReqDTO.GetMyMissions dto
    ) {
        MemberMission.Status status = toMissionStatus(dto.status());

        Sort sortInfo;
        if (dto.sort() != null && !dto.sort().isBlank()) {
            sortInfo = Sort.by(dto.sort()).descending();
        } else {
            sortInfo = Sort.by("mission.id").descending();
        }

        PageRequest pageRequest = PageRequest.of(dto.pageNumber(), dto.pageSize(), sortInfo);
        Page<MemberMission> page = memberMissionRepository.findAllByMember_IdAndStatus(dto.memberId(), status, pageRequest);

        return MissionConverter.toOffsetPagination(page.map(MissionConverter::toMyMission));
    }

    private MemberMission.Status toMissionStatus(String status) {
        try {
            return MemberMission.Status.valueOf(status.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new MissionException(MissionErrorCode.STATUS_NOT_VALID);
        }
    }
}
