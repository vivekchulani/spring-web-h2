package com.microservice.spring.web;

import com.microservice.spring.web.domain.Employee;
import com.microservice.spring.web.repository.EmployeeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDatabase {

    @Bean
    CommandLineRunner initialize(EmployeeRepository employeeRepository) {

        return args -> {
            employeeRepository.save(new Employee("Vivek", "Software Engineer"));
            employeeRepository.save(new Employee("Maine", "Product Manager"));
        };

    }

}
