package com.example.umc10th.domain.store.entity;

import com.example.umc10th.domain.address.entity.Address;
import com.example.umc10th.domain.food.entity.FoodType;
import com.example.umc10th.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "store")
public class Store extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "store_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "`key`")
    private FoodType foodType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "detail_address_id")
    private Address detailAddress;

    private String name;

    @Column(name = "store_number")
    private String storeNumber;

    @Column(name = "field")
    private String field;
}
