package com.employee.amaris.controller;

import com.employee.amaris.model.Employee;
import com.employee.amaris.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @GetMapping("/list-employees")
    public ResponseEntity<List<Employee>> listEmployees() {
        try {
            return employeeService.listAllEmployees();
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/employee/{id}")
    public ResponseEntity<Employee> getEmployee (@PathVariable String id) {
        if (id == null || id.isEmpty() ) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        try {
            return employeeService.getSingleEmployee(id);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

