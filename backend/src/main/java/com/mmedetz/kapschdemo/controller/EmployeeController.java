package com.mmedetz.kapschdemo.controller;

import com.mmedetz.kapschdemo.controller.exception.BadRequestException;
import com.mmedetz.kapschdemo.controller.exception.ResourceNotFoundException;
import com.mmedetz.kapschdemo.model.Department;
import com.mmedetz.kapschdemo.model.Employee;
import com.mmedetz.kapschdemo.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/employees/")
public class EmployeeController {

    private static Logger LOG = LoggerFactory.getLogger(EmployeeController.class);

    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    /**
     * Returns all employees
     * @return a list of all employees
     */
    @GetMapping("/")
    public List<Employee> getAll() {
        LOG.info("Getting all employees!");
        List<Employee> list = employeeRepository.findAll();
        list.sort(Employee::compareTo);
        return list;
    }

    /**
     * Returns a employee by a given id
     * @param id of the employee
     * @return a employee with the given id
     * @throws ResourceNotFoundException if the id can not be found
     */
    @GetMapping("/{id}")
    public Employee getByID(@PathVariable Long id) throws ResourceNotFoundException {
        LOG.info("Getting employee by id: " + id);
        return employeeRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
    }

    /**
     * Returns all employees of a department
     * @param department of the employees
     * @return a list of employees of the department
     */
    @GetMapping("/getByDepartment")
    public List<Employee> getByDepartment(@RequestBody Department department) {
        LOG.info("Getting employees by department: " + department);
        return employeeRepository.findByDepartment(department);
    }

    /**
     * Creates or updates an empleyee.
     * If the id of the employee is set the employee is updated, otherwise a new employee is created.
     * @param employee to create or update
     * @return the updated or created employee
     */
    @PostMapping("/")
    public Employee createOrUpdate(@RequestBody Employee employee) {
        LOG.info("Creating or updating: " + employee);
        validate(employee);
        employeeRepository.save(employee);
        return employee;
    }

    /**
     * Deletes an employee by its id.
     * @param id of the employee
     * @return the deleted employee
     */
    @DeleteMapping("{id}")
    public Employee delete(@PathVariable Long id) {
        LOG.info("Deleting employee with id: " + id);
        var employee = getByID(id);
        employeeRepository.delete(employee);
        return employee;
    }

    private void validate(Employee employee) throws BadRequestException {
        if(employee.getName() == null || employee.getEmail() == null || employee.getEntryDate() == null)
            throw new BadRequestException("Not all attributes of the employee are set!");

        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(employee.getEmail());
        if(!matcher.find())
            throw new BadRequestException("Not valid email is set to employee!");
    }
}
