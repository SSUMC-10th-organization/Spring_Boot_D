package com.example.umc_week4.domain.member.repository;

import com.example.umc_week4.domain.member.entity.Term;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TermRepository extends JpaRepository<Term, Long> {

    List<Term> findAllByEssentialTrue();
}