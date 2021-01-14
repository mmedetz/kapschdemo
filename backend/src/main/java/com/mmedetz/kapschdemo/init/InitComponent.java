package com.mmedetz.kapschdemo.init;

import com.mmedetz.kapschdemo.controller.EmployeeController;
import com.mmedetz.kapschdemo.model.Department;
import com.mmedetz.kapschdemo.model.Employee;
import com.mmedetz.kapschdemo.repository.DepartmentRepository;
import com.mmedetz.kapschdemo.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDate;

@Component
public class InitComponent {

    private static Logger LOG = LoggerFactory.getLogger(EmployeeController.class);

    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;

    @Autowired
    public InitComponent(EmployeeRepository employeeRepository, DepartmentRepository departmentRepository) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
    }

    @PostConstruct
    public void init() {

        Department[] deps = new Department[3];

        deps[0] = departmentRepository.save(new Department("Warehouse"));
        deps[1] = departmentRepository.save(new Department("Distribution"));
        deps[2] = departmentRepository.save(new Department("Management"));

        for(int i = 0; i < 50; i++) {
            var name =  "employee" + (i + 1);
            employeeRepository.save(new Employee(name, name + "@test.com", LocalDate.now(), deps[i % 3]));
        }
    }

}
