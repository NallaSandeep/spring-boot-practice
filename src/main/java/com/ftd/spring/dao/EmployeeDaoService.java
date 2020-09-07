package com.ftd.spring.dao;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.ftd.spring.exceptions.EmployeeNotFoundException;
import com.ftd.spring.pojos.Employee;

@Component
public class EmployeeDaoService {

    private static Map<Long, Employee> employeeRepo = new HashMap<>();
    static {
        Employee employee1 = new Employee();
        employee1.setId(1);
        employee1.setName("Sandeep");
        employeeRepo.put(employee1.getId(), employee1);

        Employee employee2 = new Employee();
        employee2.setId(2);
        employee2.setName("Nalla");
        employeeRepo.put(employee2.getId(), employee2);
    }
    
    public List<Employee> findAll(int count){
        return employeeRepo.values().stream().limit(count).collect(Collectors.toList());
    }
    
    public List<Employee> findAll(){
        return employeeRepo.values().stream().collect(Collectors.toList());
    }
    
    public Employee save(Employee employee) {
        employeeRepo.put(employee.getId(), employee);
        return employee;
    }
    
    public Employee findOne(Long id) {
        Employee employee = employeeRepo.get(id);
        if (employee == null) {
            throw new EmployeeNotFoundException("id: " + id);
        }
        return employeeRepo.get(id);
    }
    
    public Employee update(long id, Employee employee) {
        employeeRepo.remove(id);
        employee.setId(id);
        employeeRepo.put(id, employee);
        return employeeRepo.get(id);
    }
    
    public String delete(long id) {
        employeeRepo.remove(id);
        return "Employee is deleted successsfully";
    }

}
