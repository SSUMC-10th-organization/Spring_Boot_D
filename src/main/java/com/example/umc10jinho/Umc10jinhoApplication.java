package com.example.umc10jinho;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class Umc10jinhoApplication {

	public static void main(String[] args) {
		SpringApplication.run(Umc10jinhoApplication.class, args);
	}

}
