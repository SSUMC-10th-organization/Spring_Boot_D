package com.example.umc10thmission4.domain.store.entity.mapping;

import com.example.umc10thmission4.domain.common.BaseEntity;
import com.example.umc10thmission4.domain.store.entity.Store;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "store_category")
public class StoreCategory extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 15)
    private String categoryName;

    @Builder.Default
    @OneToMany(mappedBy = "storeCategory", cascade = CascadeType.ALL)
    private List<Store> storeList = new ArrayList<>();
}
