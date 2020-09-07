package com.ftd.spring.pojos;

import java.util.List;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class Employees {

    private List<Employee> employees;
}
