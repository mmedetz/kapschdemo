package com.mmedetz.kapschdemo.controller;

import com.mmedetz.kapschdemo.model.Department;
import com.mmedetz.kapschdemo.model.Employee;
import com.mmedetz.kapschdemo.repository.DepartmentRepository;
import com.mmedetz.kapschdemo.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.regex.Pattern;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/departments/")
public class DepartmentController {

    private static Logger LOG = LoggerFactory.getLogger(EmployeeController.class);
    private final DepartmentRepository departmentRepository;

    @Autowired
    public DepartmentController(EmployeeRepository employeeRepository, DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @GetMapping("/")
    public List<Department> getAll() {
        LOG.info("Getting all departments!");
        return departmentRepository.findAll();
    }
}
