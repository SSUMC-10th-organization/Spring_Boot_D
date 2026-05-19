package com.example.umc_week4.domain.member.repository;

import com.example.umc_week4.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByNameAndDeletedAtIsNull(String name);

    void deleteByName(String name);

    Optional<Member> findByEmail(String email);
}