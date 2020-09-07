package com.ftd.spring.pojos;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class Employee {

    /* @NonNull */
    private long id;

    /* @NonNull */
    private String name;

    /* @NonNull */
    private String designation;
}
