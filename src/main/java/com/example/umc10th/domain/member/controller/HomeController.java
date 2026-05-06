package com.example.umc10th.domain.member.controller;

import com.example.umc10th.domain.member.dto.HomeResponse;
import com.example.umc10th.domain.member.service.HomeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/home")
public class HomeController implements HomeControllerDocs{

    private final HomeService homeService;

    @Override
    @GetMapping("{addressId}/missions")
    public ResponseEntity<HomeResponse.AvailableMissionListDTO> getMissionsForHome(
            @PathVariable("addressId") Long addressId,
            @RequestParam(name = "page", defaultValue = "0") Integer page,
            @RequestParam(name = "size", defaultValue = "10") Integer size) {

        // Pageable 객체 생성
        Pageable pageable = PageRequest.of(page, size);

        // 비즈니스 로직 호출
        HomeResponse.AvailableMissionListDTO response = homeService.getAvailableMissionsByRegion(addressId, pageable);

        // 응답
        return ResponseEntity.ok(response);
    }
}