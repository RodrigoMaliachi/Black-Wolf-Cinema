package com.uady.blackWolfCinema;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.thymeleaf.dialect.springdata.SpringDataDialect;

@SpringBootApplication
@EnableScheduling
public class BlackWolfCinemaApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlackWolfCinemaApplication.class, args);
	}

	@Bean
	public SpringDataDialect springDataDialect() {
		return new SpringDataDialect();
	}




}
