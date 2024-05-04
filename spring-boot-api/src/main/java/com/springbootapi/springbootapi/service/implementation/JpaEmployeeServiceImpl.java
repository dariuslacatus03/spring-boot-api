package com.springbootapi.springbootapi.service.implementation;

import com.springbootapi.springbootapi.model.Employee;
import com.springbootapi.springbootapi.repository.JpaEmployeeRepository;
import com.springbootapi.springbootapi.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Qualifier(value = "mySQLEmployeeService")
public class JpaEmployeeServiceImpl implements EmployeeService {
    private final JpaEmployeeRepository jpaEmployeeRepository;
    @Override
    public Employee addEmployee(Employee employee) {
        return jpaEmployeeRepository.save(employee);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return jpaEmployeeRepository.findAll();
    }

    @Override
    public Employee findById(Integer id) {
        return jpaEmployeeRepository.findById(id).get();
    }

    @Override
    public Employee updateEmployee(Employee employee) {
        Optional<Employee> existingEmployee = jpaEmployeeRepository.findById(employee.getId());
        if (existingEmployee.isPresent()) {
            return jpaEmployeeRepository.save(employee);
        } else {
            throw new RuntimeException("Employee with ID " + employee.getId() + " not found");
        }
    }

    @Override
    public Boolean deleteById(Integer id) {
        try
        {
            jpaEmployeeRepository.deleteById(id);
            return Boolean.TRUE;
        }
        catch (Exception exception)
        {
            return Boolean.FALSE;
        }
    }
}
























