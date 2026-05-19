package com.example.umc10th.domain.member.dto;

import com.example.umc10th.domain.address.enums.RegionAddress;
import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.enums.Gender;
import jakarta.validation.constraints.NotNull;

import javax.management.relation.Role;
import java.time.LocalDate;

public class MemberRequest {

    public record OngoingMissionDTO (
            @NotNull(message = "memberId는 필수입니다.")
            Long memberId
    ) {}

    public record JoinDTO(
            String email,
            String password,
            String name,
            String nickname,
            String phoneNumber,
            LocalDate birth,
            Gender gender,
            RegionAddress address
    ) {}
}
