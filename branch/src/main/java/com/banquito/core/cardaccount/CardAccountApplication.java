package com.banquito.core.cardaccount;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class CardAccountApplication {

	public static void main(String[] args) {
		SpringApplication.run(CardAccountApplication.class, args);
	}

}
