package com.example.umc10thmission4;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing // 이 줄을 꼭 추가해 주세요!
public class Umc10thmission4Application {
	public static void main(String[] args) {
		SpringApplication.run(Umc10thmission4Application.class, args);
	}
}