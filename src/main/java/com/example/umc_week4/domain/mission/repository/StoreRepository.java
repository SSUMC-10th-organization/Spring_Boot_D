package com.example.umc_week4.domain.mission.repository;

import com.example.umc_week4.domain.mission.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store, Long> {
}