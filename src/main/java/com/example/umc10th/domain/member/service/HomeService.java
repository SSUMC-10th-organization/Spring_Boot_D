package com.example.umc10th.domain.member.service;

import com.example.umc10th.domain.address.repository.AddressRepository;
import com.example.umc10th.domain.member.converter.HomeConverter;
import com.example.umc10th.domain.member.dto.HomeResponse;
import com.example.umc10th.domain.mission.entity.Mission;
import com.example.umc10th.domain.mission.repository.MissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class HomeService {

    private final MissionRepository missionRepository;
    private final AddressRepository addressRepository; // (필요시)

    public HomeResponse.AvailableMissionListDTO getAvailableMissionsByRegion(Long addressId, Pageable pageable) {
        // (선택) 해당 주소 ID가 실제로 존재하는지 검증 로직 추가 가능
        if (!addressRepository.existsById(addressId)) {
            throw new IllegalArgumentException("존재하지 않는 지역입니다.");
        }

        // 오늘 날짜를 기준으로 마감되지 않은 미션 페이징 조회
        LocalDate today = LocalDate.now();
        Page<Mission> missionPage = missionRepository.findAvailableMissionsByAddressId(addressId, today, pageable);

        // Converter를 사용하여 변환 후 반환
        return HomeConverter.toAvailableMissionListDTO(missionPage);
    }
}
