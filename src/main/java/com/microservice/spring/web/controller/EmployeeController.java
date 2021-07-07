package com.microservice.spring.web.controller;

import com.microservice.spring.web.domain.Employee;
import com.microservice.spring.web.exception.EmployeeNotFoundException;
import com.microservice.spring.web.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {

    private final EmployeeRepository employeeRepository;

    @Value("${test.name:defaultValue}") // inject test.name property from application.properties file
    private String name;

    @Autowired
    private Environment environment;

    public EmployeeController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @GetMapping(value = "/val")
    public String getMsgUsingValue() {
        return name;
    }

    @GetMapping(value = "/env")
    public String getMsgUsingEnvironment() {
        return environment.getProperty("test.env");
    }

    @GetMapping(value = "/location")
    public String getLocation() {
        return environment.getProperty("location");
    }

    @GetMapping("/employees")
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @GetMapping("/employees/{id}")
    public Employee getEmployee(@PathVariable Long id) {
        Employee employee =  employeeRepository.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));

        return employee;
    }

    @PostMapping("/employees")
    public Employee saveEmployee(@RequestBody Employee employee) {
        return employeeRepository.save(employee);
    }

    @PutMapping("/employees/{id}")
    public Employee updateEmployee(@RequestBody Employee employee, @PathVariable Long id) {

        return employeeRepository.findById(id).map(emp -> {
            emp.setName(employee.getName());
            emp.setRole(employee.getRole());
            return employeeRepository.save(emp);
        }).orElseGet(() -> {
            employee.setId(id);
            return employeeRepository.save(employee);
        });
    }

    @DeleteMapping("/employees/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable Long id) {
        employeeRepository.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
