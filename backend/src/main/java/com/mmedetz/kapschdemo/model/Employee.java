package com.mmedetz.kapschdemo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class Employee implements Comparable<Employee>{

    public Employee() {
    }

    public Employee(String name, String email, LocalDate entryDate, Department department) {
        this.name = name;
        this.email = email;
        this.entryDate = entryDate;
        this.department = department;
    }


    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String name;

    @Column
    private String email;

    @Column
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate entryDate;


    @ManyToOne
    @JoinColumn(name = "department_id", nullable = true)
    private Department department;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(LocalDate entryDate) {
        this.entryDate = entryDate;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", entryDate=" + entryDate +
                ", department=" + department +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(id, employee.id) &&
                Objects.equals(name, employee.name) &&
                Objects.equals(email, employee.email) &&
                Objects.equals(entryDate, employee.entryDate) &&
                Objects.equals(department, employee.department);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, entryDate, department);
    }

    @Override
    public int compareTo(Employee o) {
        return name.compareTo(o.name);
    }
}
