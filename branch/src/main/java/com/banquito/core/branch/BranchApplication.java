package com.banquito.core.branch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class BranchApplication {
    public static void main(String[] args) {
        SpringApplication.run(BranchApplication.class, args);
    }
} 