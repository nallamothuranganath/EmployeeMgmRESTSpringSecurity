package com.gl.employee.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class Employee {

    private long employeeId;
    private String firstName;
    private String lastName;
    private String email;
}
