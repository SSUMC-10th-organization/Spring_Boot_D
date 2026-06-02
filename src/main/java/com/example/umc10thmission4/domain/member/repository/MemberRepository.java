package com.example.umc10thmission4.domain.member.repository;

import com.example.umc10thmission4.domain.member.entity.Member;
import com.example.umc10thmission4.domain.member.enums.SocialProvider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByEmail(String email);
    Optional<Member> findBySocialProviderAndSocialUid(SocialProvider socialProvider, String socialUid);
}
