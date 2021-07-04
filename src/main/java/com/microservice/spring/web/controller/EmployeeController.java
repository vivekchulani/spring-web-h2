package com.microservice.spring.web.controller;

import com.microservice.spring.web.domain.Employee;
import com.microservice.spring.web.exception.EmployeeNotFoundException;
import com.microservice.spring.web.repository.EmployeeRepository;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {

    private final EmployeeRepository employeeRepository;

    public EmployeeController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
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
    public void deleteEmployee(@PathVariable Long id) {
        employeeRepository.deleteById(id);
    }
}
