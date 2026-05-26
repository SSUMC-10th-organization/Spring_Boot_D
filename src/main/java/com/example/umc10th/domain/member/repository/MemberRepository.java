package com.example.umc10th.domain.member.repository;

import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.mission.entity.Mission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    @Query("SELECT m FROM Mission m JOIN FETCH m.store s " +
            "WHERE s.detailAddress.id = :addressId " +
            "AND m.deadline >= :today")
    Page<Mission> findAvailableMissionsByAddressId(
            @Param("addressId") Long addressId,
            @Param("today") LocalDate today,
            Pageable pageable
    );

    Optional<Member> findByEmail(String email);

    boolean existsByEmail(String email);
}
