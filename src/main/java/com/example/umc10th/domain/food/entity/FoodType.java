package com.example.umc10th.domain.food.entity;

import com.example.umc10th.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "food_type")
public class FoodType extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`key`") // Key는 예약어일 수 있으므로 backtick 사용 권장
    private Long id;

    @Column(name = "food_type")
    private String foodType; // ex) Korean, Japanese
}
