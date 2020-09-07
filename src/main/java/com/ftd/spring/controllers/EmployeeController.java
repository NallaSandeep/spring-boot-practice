package com.ftd.spring.controllers;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ftd.spring.dao.EmployeeDaoService;
import com.ftd.spring.pojos.Employee;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping(value = "/employees")
@RestController
public class EmployeeController {

    @Autowired
    public EmployeeDaoService employeeDaoService;

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") long id) {
        return new ResponseEntity<>(employeeDaoService.delete(id), HttpStatus.OK);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Object> updateEmployee(@PathVariable("id") long id, @RequestBody Employee employee) {
        return new ResponseEntity<>(employeeDaoService.update(id, employee), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Object> getEmployee(@PathVariable("id") long id) {
        return new ResponseEntity<>(employeeDaoService.findOne(id), HttpStatus.OK);
    }

    @PostMapping(value = "")
    public ResponseEntity<Object> createEmployee(@RequestBody @Valid Employee employee) {
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(employee.getId()).toUri();
        return ResponseEntity.created(location).body(employee);
    }

    @GetMapping(value = { "", "/get" }, produces = { MediaType.APPLICATION_JSON_VALUE,
        MediaType.APPLICATION_XML_VALUE })
    public List<Employee> getEmployees(@RequestParam(required = false) Integer count) {
        if (count == null) {
            return employeeDaoService.findAll();
        }
        return employeeDaoService.findAll(count);
    }

    @GetMapping(value = "/hello", headers = { "key1=value1" }, produces = "application/json")
    public String hello() {
        return "Sandeep, Nalla";
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    void onIllegalArgumentException(IllegalArgumentException exception) {
        log.info(exception.getMessage());
    }

    void throwException() {
        throw new IllegalArgumentException("I'm an exception");
    }

}
