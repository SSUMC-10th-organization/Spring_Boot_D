package com.example.umc_week4.domain.member.repository;

import com.example.umc_week4.domain.member.entity.Member;
import com.example.umc_week4.domain.member.exception.MemberException;
import com.example.umc_week4.domain.member.exception.code.MemberErrorCode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
    void deleteByName(String name);



}
