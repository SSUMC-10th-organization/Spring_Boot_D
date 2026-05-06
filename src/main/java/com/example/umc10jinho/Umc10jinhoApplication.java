package com.example.umc10jinho;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.jdbc.autoconfigure.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class Umc10jinhoApplication {

	public static void main(String[] args) {
		SpringApplication.run(Umc10jinhoApplication.class, args);
	}

}
