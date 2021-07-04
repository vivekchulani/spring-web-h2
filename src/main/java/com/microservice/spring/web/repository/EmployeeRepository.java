package com.microservice.spring.web.repository;

import com.microservice.spring.web.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
