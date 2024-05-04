package com.springbootapi.springbootapi.resource;

import com.springbootapi.springbootapi.model.Employee;
import com.springbootapi.springbootapi.service.EmployeeService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.*;

@RestController
//@RequiredArgsConstructor
@RequestMapping(path = "/api/employees")
public class EmployeeResource {

    private final EmployeeService employeeService;
    public EmployeeResource(@Qualifier(value = "mySQLEmployeeService")EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public ResponseEntity<List<Employee>> getEmployees()
    {
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }

    @GetMapping("{id}")
    public ResponseEntity<Employee> getEmployee(@PathVariable("id") Integer id)
    {
        return ResponseEntity.ok(employeeService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee)
    {
        //employee.setId(employeeService.getAllEmployees().size() + 1);
        return ResponseEntity.created(getLocation(employee.getId())).body(employeeService.addEmployee(employee));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Boolean> deleteEmployee(@PathVariable("id") Integer id)
    {
        return ResponseEntity.ok(employeeService.deleteById(id));
    }

    @PutMapping()
    public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee)
    {
        return ResponseEntity.created(getLocation(employee.getId())).body(employeeService.updateEmployee(employee));
    }

    protected static URI getLocation(Integer id) {
        return fromCurrentRequest().path("{id}").buildAndExpand(id).toUri();
    }
}
















