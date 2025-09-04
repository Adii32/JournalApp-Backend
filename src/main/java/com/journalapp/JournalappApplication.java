package com.journalapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.client.RestTemplate;

import org.springframework.scheduling.annotation.EnableScheduling;
import jakarta.persistence.EntityManagerFactory;

@SpringBootApplication
@EnableScheduling
public class JournalappApplication {

	public static void main(String[] args) {
		SpringApplication.run(JournalappApplication.class, args);
	}
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
	
}
