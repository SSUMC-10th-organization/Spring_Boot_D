package com.example.umc_week4.domain.member.repository;


import com.example.umc_week4.domain.member.entity.Food;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodRepository extends JpaRepository<Food, Long> {
}
