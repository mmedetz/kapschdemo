package com.mmedetz.kapschdemo.repository;

import com.mmedetz.kapschdemo.model.Department;
import com.mmedetz.kapschdemo.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    public List<Employee> findByDepartment(@Param("user") Department department);
}
