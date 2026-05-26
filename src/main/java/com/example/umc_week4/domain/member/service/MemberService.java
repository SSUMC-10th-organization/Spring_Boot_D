package com.example.umc_week4.domain.member.service;

import com.example.umc_week4.domain.member.converter.MemberConverter;
import com.example.umc_week4.domain.member.dto.MemberReqDTO;
import com.example.umc_week4.domain.member.dto.MemberResDTO;
import com.example.umc_week4.domain.member.entity.Food;
import com.example.umc_week4.domain.member.entity.Member;
import com.example.umc_week4.domain.member.entity.Term;
import com.example.umc_week4.domain.member.entity.mapping.MemberFood;
import com.example.umc_week4.domain.member.entity.mapping.MemberTerm;
import com.example.umc_week4.domain.member.exception.MemberException;
import com.example.umc_week4.domain.member.exception.code.MemberErrorCode;
import com.example.umc_week4.domain.member.repository.*;
import com.example.umc_week4.domain.mission.dto.HomeMissionDTO;
import com.example.umc_week4.domain.mission.entity.mapping.MemberMission;
import com.example.umc_week4.domain.mission.repository.MemberMissionRepository;
import com.example.umc_week4.domain.mission.repository.MissionRepository;
import com.example.umc_week4.global.apiPayload.code.GeneralErrorCode;
import com.example.umc_week4.global.apiPayload.exception.ProjectException;
import com.example.umc_week4.global.security.entity.AuthMember;
import com.example.umc_week4.global.security.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final MissionRepository missionRepository;
    private final MemberMissionRepository memberMissionRepository;

    //회원가입
    private final PasswordEncoder passwordEncoder;
    private final TermRepository termRepository;
    private final FoodRepository foodRepository;
    private final MemberTermRepository memberTermRepository;
    private final MemberFoodRepository memberFoodRepository;


    //로그인
    private final JwtUtil jwtUtil;

    @Transactional(readOnly = true)
    public MemberResDTO.GetInfo getMyPage(String name) {
        Member member = memberRepository.findByNameAndDeletedAtIsNull(name)
                .orElseThrow(() -> new ProjectException(MemberErrorCode.MEMBER_NOT_FOUND));

        return MemberConverter.toGetInfo(member);
    }

    public MemberResDTO.GetInfo getInfo(
            AuthMember member
    ) {
        return MemberConverter.toGetInfo(member.getMember());
    }

    @Transactional(readOnly = true)
    public MemberResDTO.HomeInfo getHome(
            Long memberId,
            String locationName,
            Long cursor,
            Integer size
    ) {
        Long searchCursor = getSearchCursor(cursor);

        Integer searchSize = size + 1;

        List<HomeMissionDTO> rows = missionRepository.findHomeMissions(
                locationName,
                searchCursor,
                PageRequest.of(0, searchSize)
        );

        Boolean hasNext = rows.size() > size;
        List<MemberResDTO.HomeMission> missions = new ArrayList<>();

        Long totalCompletedCount = memberMissionRepository.countByMember_IdAndStatus(
                memberId,
                MemberMission.Status.COMPLETE
        );

        int resultSize = Math.min(rows.size(), size);
        Long nextCursor = null;

        for (int i = 0; i < resultSize; i++) {
            HomeMissionDTO row = rows.get(i);

            nextCursor = row.getCursorValue();

            missions.add(new MemberResDTO.HomeMission(
                    row.getMissionId(),
                    row.getStoreId(),
                    row.getStoreName(),
                    row.getMissionCondition(),
                    row.getMissionReward(),
                    row.getCursorValue()
            ));
        }

        return new MemberResDTO.HomeInfo(
                locationName,
                totalCompletedCount,
                nextCursor,
                hasNext,
                missions
        );
    }

    private Long getSearchCursor(Long cursor) {
        if (cursor == null || cursor == 0) {
            return Long.MAX_VALUE;
        }
        return cursor;
    }

    //회원가입 메서드
    @Transactional
    public MemberResDTO.SignupResult signup(MemberReqDTO.Signup request) {
        validateDuplicateEmail(request.email());

        String encodedPassword = passwordEncoder.encode(request.password());

        Member member = MemberConverter.toMember(request, encodedPassword);

        Member savedMember = memberRepository.save(member);

        saveAgreedTerms(savedMember, request.agreedTerms());

        savePreferFoods(savedMember, request.preferFoodTypes());

        return new MemberResDTO.SignupResult(
                savedMember.getName(),
                savedMember.getEmail()
        );
    }
    //중복 검사
    private void validateDuplicateEmail(String email) {
        if (memberRepository.existsByEmail(email)) {
            throw new MemberException(MemberErrorCode.DUPLICATE_EMAIL);
        }
    }

    // 로그인 메서드
    @Transactional(readOnly = true)
    public MemberResDTO.Login login(MemberReqDTO.Login request) {
        Member member = memberRepository.findByEmail(request.email())
                .orElseThrow(() -> new MemberException(GeneralErrorCode.UNAUTHORIZED));

        if (!passwordEncoder.matches(request.password(), member.getPassword())) {
            throw new MemberException(GeneralErrorCode.UNAUTHORIZED);
        }

        String accessToken = jwtUtil.createAccessToken(new AuthMember(member));

        return MemberConverter.toLogin(accessToken);
    }

    //약관 저장
    private void saveAgreedTerms(Member member, List<Long> agreedTermIds) {

        validateRequiredTerms(agreedTermIds);

        Set<Long> agreedTermIdSet = Set.copyOf(agreedTermIds);

        List<Term> terms = termRepository.findAllById(agreedTermIdSet);

        if (terms.size() != agreedTermIdSet.size()) {
            throw new MemberException(MemberErrorCode.TERM_NOT_FOUND);
        }

        List<MemberTerm> memberTerms = terms.stream()
                .map(term -> MemberTerm.builder()
                        .member(member)
                        .term(term)
                        .build())
                .toList();

        memberTermRepository.saveAll(memberTerms);
    }
    //필수 약관 동의 검사
    private void validateRequiredTerms(List<Long> agreedTermIds) {

        Set<Long> agreedTermIdSet = Set.copyOf(agreedTermIds);

        List<Term> requiredTerms = termRepository.findAllByEssentialTrue();

        boolean allRequiredAgreed = requiredTerms.stream()
                .allMatch(term -> agreedTermIdSet.contains(term.getId()));

        if (!allRequiredAgreed) {
            throw new MemberException(MemberErrorCode.REQUIRED_TERM_NOT_AGREED);
        }
    }
    //선호 음식 저장
    private void savePreferFoods(Member member, List<Integer> preferFoodTypes) {

        Set<Long> foodIds = preferFoodTypes.stream()
                .map(Integer::longValue)
                .collect(Collectors.toSet());

        List<Food> foods = foodRepository.findAllById(foodIds);

        if (foods.size() != foodIds.size()) {
            throw new MemberException(MemberErrorCode.FOOD_NOT_FOUND);
        }

        List<MemberFood> memberFoods = foods.stream()
                .map(food -> MemberFood.builder()
                        .member(member)
                        .food(food)
                        .build())
                .toList();

        memberFoodRepository.saveAll(memberFoods);
    }


}
