package com.springbootapi.springbootapi.repository;

import com.springbootapi.springbootapi.model.Employee;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class InMemoryEmployeeRepository {
    private static final List<Employee> DATABASE = new ArrayList<>();

    static {
        DATABASE.add(new Employee(1, "John", "Smith", "johnS@gmail.com"));
        DATABASE.add(new Employee(1, "Maria", "Smith", "mariaS@gmail.com"));
        DATABASE.add(new Employee(1, "Andrew", "Smith", "andrewS@gmail.com"));
    }

    public void addEmployee(Employee employee)
    {
        DATABASE.add(employee);
    }

    public List<Employee> getAllEmployees()
    {
        return List.copyOf(DATABASE);
    }

    public Employee findById(Integer id){
        return DATABASE
                .stream()
                .filter(employee -> id.equals(employee.getId()))
                .findFirst()
                .orElseThrow();
    }

    public void updateEmployee(Employee employee)
    {
        DATABASE.stream()
                .filter(emp -> emp.getId().equals(employee.getId()))
                .findFirst()
                .ifPresentOrElse(
                        emp -> {
                            int index = DATABASE.indexOf(emp);
                            DATABASE.set(index, employee);
                        },
                        () -> {
                            throw new RuntimeException("Employee with ID " + employee.getId() + " not found.");
                        }
                );
    }

    public Boolean deleteById(Integer id)
    {
        Employee employeeToDelete = DATABASE
                .stream()
                .filter(employee -> id.equals(employee.getId()))
                .findFirst()
                .orElseThrow();
        DATABASE.remove(employeeToDelete);
        return Boolean.TRUE;
    }
}







































