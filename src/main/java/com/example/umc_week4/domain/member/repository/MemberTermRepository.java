package com.example.umc_week4.domain.member.repository;

import com.example.umc_week4.domain.member.entity.mapping.MemberTerm;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberTermRepository extends JpaRepository<MemberTerm, Long> {
}