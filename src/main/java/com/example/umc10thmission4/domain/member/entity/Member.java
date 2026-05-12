package com.example.umc10thmission4.domain.member.entity;

import jakarta.persistence.Entity;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Member extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 20)
    private String name;

    // 양방향 매핑: Member에서 선호 음식 목록을 조회하고 싶을 때 추가
    // MemberFood 엔티티에 있는 'member' 필드에 의해 매핑됨
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<MemberFood> memberFoodList = new ArrayList<>();
}
