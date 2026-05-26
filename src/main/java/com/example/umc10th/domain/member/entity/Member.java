package com.example.umc10th.domain.member.entity;

import com.example.umc10th.domain.address.entity.Address;
import com.example.umc10th.domain.address.enums.RegionAddress;
import com.example.umc10th.domain.member.enums.Gender;
import com.example.umc10th.domain.member.enums.LoginType;
import com.example.umc10th.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "member")
public class Member extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "detail_address_id")
    private Address detailAddress;

    private String name;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private LocalDate birth;

    @Enumerated(EnumType.STRING)
    private RegionAddress address;

    @Column(name = "nickname")
    private String nickname;

    @Enumerated(EnumType.STRING)
    @Column(name = "login_type")
    private LoginType loginType;

    @Builder.Default
    private Long point = 0L;

    private String email;

    private String password;

    @Column(name = "phone_number")
    private String phoneNumber;
}
