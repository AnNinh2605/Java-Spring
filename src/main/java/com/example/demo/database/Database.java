package com.example.demo.database;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.demo.models.Employer;
import com.example.demo.repositories.EmployerRepository;

@Configuration
public class Database {
    // logger
    private static final Logger logger = LoggerFactory.getLogger(Database.class);
    @Bean
    CommandLineRunner initDatabase(EmployerRepository employerRepository) {
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                Employer employer_1 = new Employer("employer1@gmail.com", "employer1", 1, "employer1");
                Employer employer_2 = new Employer("employer2@gmail.com", "employer2", 2, "employer2");
                logger.info("insert data: " + employerRepository.save(employer_1));
                logger.info("insert data: " + employerRepository.save(employer_2));
            }
        };
    }
}
