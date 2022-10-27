package com.gl.employee.dto;

import lombok.*;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class Role {


    private long id;
    private String name;
    private LocalDate creationDate;
    private char enabledFlag;
}
