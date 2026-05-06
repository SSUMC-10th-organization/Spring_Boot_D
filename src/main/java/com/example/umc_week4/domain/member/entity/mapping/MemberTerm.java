package com.example.umc_week4.domain.member.entity.mapping;

import com.example.umc_week4.domain.member.entity.Member;
import com.example.umc_week4.domain.member.entity.Term;
import com.example.umc_week4.global.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "member_term")
public class MemberTerm extends BaseEntity {


    //ERD의 멤버, 약관 매핑 테이블
    //id, memberid, termid 존재함
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "term_id")
    private Term term;
}